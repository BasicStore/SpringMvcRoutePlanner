package com.routeplanner.ctrl;
import java.time.LocalDate;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.routeplanner.shopping.Basket;
import com.routeplanner.shopping.CardType;
import com.routeplanner.shopping.ContactDetails;
import com.routeplanner.shopping.Order;
import com.routeplanner.shopping.PaymentInfo;
import com.routeplanner.shopping.Purchase;
import com.routeplanner.shopping.Shopping;
import com.routeplanner.shopping.Ticket;
import com.routeplanner.shopping.service.BasketService;
import com.routeplanner.shopping.service.OrderService;
import com.routeplanner.shopping.service.PaymentInfoService;
import com.routeplanner.shopping.service.PurchaseService;
import com.routeplanner.shopping.utils.FormValidation;


@Controller
@RequestMapping("/routeplanner")
public class CheckoutController {

	private static final Logger logger = LoggerFactory.getLogger(CheckoutController.class);
	
	@Autowired
	private ControllerHelper controllerHelper;
	
	@Autowired
	private PaymentInfoService paymentInfoService;
	
	@Autowired
	private PurchaseService purchaseService;
	
	
	public CheckoutController() {
		
	}

	
	@PostMapping("/go-to-checkout-person-details")
	public ModelAndView goToCheckout(HttpServletRequest request, ModelMap model, @Valid @ModelAttribute Basket basket) {
		// if basket is empty go back to the view basket page
		Shopping shopping = (Shopping)request.getSession().getAttribute("shopping");
		if (shopping.getBasket().getTickets() == null || shopping.getBasket().getTickets().size() == 0) {
			return controllerHelper.prepareForViewBasket(request, model, basket);
		}
		
		logger.info("go to checkout from basket page. Basket = " + basket.toString());
		
		// prepare new payment info and new contact details parts of the screen
		model.addAttribute("contactDetails", new ContactDetails());
		return new ModelAndView("contact-details");
	}
	
	
	
	@PostMapping("/add-contact-details")
	public ModelAndView goToPayment(HttpServletRequest request, ModelMap model, @Valid @ModelAttribute ContactDetails contactDetails, BindingResult errors) {
		if (errors.hasErrors()) {
    		logger.info("errors exist on contactDetailsForm on contact details page");
    		model.addAttribute("errorLine1", "rp.contact.details.generic.form.error");
    		model.addAttribute("contactDetails", contactDetails);
    		FormValidation.addBespokeContactDetailsErrMsgs(contactDetails, model);
    		return new ModelAndView("contact-details");
    	}
		
		logger.info("persisting contact details as part of this order process = " + contactDetails.toString());
		paymentInfoService.saveContactDetails(contactDetails);
		
		// the user is creating an order if following the standard journey, so at this stage create a new order object
		Shopping shopping = (Shopping)request.getSession().getAttribute("shopping");
		Order order = shopping.getOrder();
		if (shopping.getOrder() == null) {
			order = new Order();
		}

		// persist order with the contact details part (without payment info), and add to shopping cart session
		order.setContactDetails(contactDetails);
		order.setBasket(shopping.getBasket());
		order.setUser(shopping.getUser());
		shopping.setOrder(order);
		
		// go to checkout page
		model.addAttribute("paymentInfo", new PaymentInfo());
		return new ModelAndView("checkout");
	}
	
	
	@PostMapping("/do-purchase")
	public ModelAndView addPaymentInfo(HttpServletRequest request, ModelMap model, @Valid @ModelAttribute PaymentInfo paymentInfo, BindingResult errors) {
		Shopping shopping = (Shopping)request.getSession().getAttribute("shopping");
		if (errors.hasErrors()) {
    		logger.info("errors exist on doPurchaseForm on checkout page");
    		model.addAttribute("errorLine1", "rp.checkout.generic.form.error");
    		model.addAttribute("paymentInfo", paymentInfo);
    		FormValidation.addBespokePaymentInfoErrMsgs(paymentInfo, model);
    		return new ModelAndView("checkout");
    	}
		
		// persist the payment info in full
		paymentInfoService.save(paymentInfo);
		logger.info("payment info added by user (excludes contact info): " + paymentInfo.toString());
		logger.info("persisted payment info in full: " + paymentInfo.toString());
		shopping.getOrder().setPaymentInfo(paymentInfo);
		
		// persist the purchase
		try {
			if (purchase(shopping.getOrder())) {
				Purchase purchase = new Purchase(shopping.getUser(), LocalDate.now(), shopping.getOrder());
				
				// TODO when the back button is involved, sometimes the basket is lost from the order.................needs fixing

				// set the basket and its contents to closed
				Basket basket = purchase.getOrder().getBasket();
				basket.setOpen(false);
				basket.closeAllTickets();
				Order order = shopping.getOrder();
				order.setBasket(basket);
				purchase.setOrder(order);
				
				purchaseService.save(purchase, basket);

				shopping.setPurchase(purchase);
				shopping.postPurchasePruneOrder();
			} else {
				// TODO go to sale-failure with explanation, and a link to payment
			}
		} catch(Throwable t) { 
			logger.info("An error occurred whilst attempting to persist the purchase data - " + t.getMessage());
    		model.addAttribute("paymentInfo", paymentInfo);
    		model.addAttribute("errorLine1", "rp.purchase.generic.failure.error");
    		return new ModelAndView("checkout");
		}
				
		return new ModelAndView("sale-confirmation");
	}
	
	
	
	// do something to actually purchase the 
	private boolean purchase(Order order) {
		return true;
	}
	
	
}
