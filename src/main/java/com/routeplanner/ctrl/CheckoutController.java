package com.routeplanner.ctrl;
import java.time.LocalDate;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.routeplanner.shopping.Basket;
import com.routeplanner.shopping.ContactDetails;
import com.routeplanner.shopping.Order;
import com.routeplanner.shopping.PaymentInfo;
import com.routeplanner.shopping.Purchase;
import com.routeplanner.shopping.RouteQuery;
import com.routeplanner.shopping.Shopping;
import com.routeplanner.shopping.Ticket;
import com.routeplanner.shopping.User;
import com.routeplanner.shopping.service.BasketService;
import com.routeplanner.shopping.service.OrderService;
import com.routeplanner.shopping.service.PaymentInfoService;
import com.routeplanner.shopping.service.PurchaseService;


@Controller
@RequestMapping("/routeplanner")
public class CheckoutController {

	private static final Logger logger = LoggerFactory.getLogger(CheckoutController.class);
	
	LoginController loginController;
	
	@Autowired
	private PaymentInfoService paymentInfoService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private PurchaseService purchaseService;
	
	@Autowired
	private BasketService basketService;
	
	// TODO this must be dynamic
	final ResourceBundle prop = ResourceBundle.getBundle("messages", Locale.FRANCE);
	
	public CheckoutController() {
		
	}

	
	@PostMapping("/go-to-checkout-person-details")
	public ModelAndView goToCheckout(HttpServletRequest request, ModelMap model, @Valid @ModelAttribute Basket basket, BindingResult errors) {
		if (errors.hasErrors()) {
    		logger.info("errors exist on submitBasketForm on view basket page");
    		model.addAttribute("basket", basket);
    		model.addAttribute("ticket", new Ticket());
    		return new ModelAndView("view-basket");
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
    		model.addAttribute("errorLine1", prop.getString("rp.contact.details.generic.form.error"));
    		model.addAttribute("contactDetails", contactDetails);
    		addBespokeErrMsgs(contactDetails, model);
    		return new ModelAndView("contact-details");
    	}
		
		logger.info("persisting contact details as part of this order process = " + contactDetails.toString());
		paymentInfoService.saveContactDetails(contactDetails);
		
		// the user is creating an order if following the standard journey, so at this stage create a new order object
		Shopping shopping = (Shopping)request.getSession().getAttribute("shopping");
		Order order = shopping.getOrder();
		if (shopping.getOrder() == null) {
			order = new Order();
			orderService.save(order);
		}
		
		shopping.setOrder(order);
		PaymentInfo paymentInfo = new PaymentInfo();
		paymentInfo.setContactDetails(contactDetails);
		
		// persist payment info
		paymentInfoService.save(paymentInfo);
		
		// persist order with the payment info (contact details part)		
		order.setPaymentInfo(paymentInfo);
		order.setBasket(shopping.getBasket());
		order.setUser(shopping.getUser());
		orderService.save(order);
		
		// go to checkout page
		model.addAttribute("paymentInfo", new PaymentInfo());
		return new ModelAndView("checkout");
	}
	
	
	
	private void addBespokeErrMsgs(ContactDetails contactDetails, ModelMap model) {
		addBlankValidation(contactDetails.getAddressLine1(), model, "badFieldAddressLine1");
		addBlankValidation(contactDetails.getCity(), model, "badFieldCity");
		addBlankValidation(contactDetails.getRegionOrState(), model, "badFieldRegion");
		addBlankValidation(contactDetails.getCountry(), model, "badFieldCountry");
		addBlankValidation(contactDetails.getEmail(), model, "badFieldEmail");
		addBlankValidation(contactDetails.getFullname(), model, "badFieldFullname");
	}
	
	
	private void addBlankValidation(String field, ModelMap model, String attribute) {
		if (StringUtils.isBlank(field)) {
			model.addAttribute(attribute, prop.getString("rp.validation.is.null"));
		}
	}
	
	
	
	
	@PostMapping("/do-purchase")
	public ModelAndView addPaymentInfo(HttpServletRequest request, ModelMap model, @Valid @ModelAttribute PaymentInfo paymentInfo, BindingResult errors) {
		Shopping shopping = (Shopping)request.getSession().getAttribute("shopping");
		if (errors.hasErrors()) {
    		logger.info("errors exist on doPurchaseForm on checkout page");
    		model.addAttribute("paymentInfo", paymentInfo);
    		return new ModelAndView("checkout");
    	}
		
		logger.info("payment info added by user (excludes contact info): " + paymentInfo.toString());
				
		// update the payment with the card details
		PaymentInfo shopPayInfo = shopping.getOrder().getPaymentInfo();
		shopPayInfo.setCardNumber(paymentInfo.getCardNumber());
		shopPayInfo.setCardType(paymentInfo.getCardType());		
		shopPayInfo.setExpiry_date(paymentInfo.getExpiry_date());
		shopPayInfo.setNameOnCard(paymentInfo.getNameOnCard());
		shopPayInfo.setSecurityCode(paymentInfo.getSecurityCode());
		
		// persist the payment info in full
		paymentInfoService.save(shopPayInfo);
		logger.info("persisted payment info in full: " + paymentInfo.toString());

		// persist the purchase
		if (purchase(shopping.getOrder())) {
			Purchase purchase = new Purchase(shopping.getUser(), LocalDate.now(), shopping.getOrder());
			
			// TODO when the back button is involved, sometimes the basket is lost from the order.................needs fixing
			Basket basket = purchase.getOrder().getBasket();
			
			basket.setOpen(false);
			basketService.save(basket);
			
			shopping.setPurchase(purchase);
			purchaseService.save(purchase);
			shopping.postPurchasePruneOrder();
		} else {
			// TODO go to sale-failure with explanation, and a link to payment
		}
				
		return new ModelAndView("sale-confirmation");
	}
	
	
	
	// do something to actually purchase the 
	private boolean purchase(Order order) {
		return true;
	}
	
	
}
