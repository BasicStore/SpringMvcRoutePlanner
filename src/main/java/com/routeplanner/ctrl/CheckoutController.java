package com.routeplanner.ctrl;
import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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

import com.routeplanner.repository.BasketRepository;
import com.routeplanner.repository.ContractDetailsRepository;
import com.routeplanner.repository.OrderRepository;
import com.routeplanner.repository.PaymentInfoRepository;
import com.routeplanner.repository.UserRepository;
import com.routeplanner.shopping.Basket;
import com.routeplanner.shopping.ContactDetails;
import com.routeplanner.shopping.Order;
import com.routeplanner.shopping.PaymentInfo;
import com.routeplanner.shopping.Purchase;
import com.routeplanner.shopping.Shopping;
import com.routeplanner.shopping.Ticket;


@Controller
@RequestMapping("/routeplanner")
public class CheckoutController {

	private static final Logger logger = LoggerFactory.getLogger(CheckoutController.class);
	
	// TODO NOT GREAT TO PUT THESE HERE, WRITE A SERVICE TIER FOR THIS.......
	@Autowired
	private BasketRepository basketRepository;
	
	@Autowired
	private PaymentInfoRepository paymentInfoRepository;
	
//	@Autowired
//	private OrderRepository orderRepository;
	
	@Autowired
	private ContractDetailsRepository contactDetailsRepository;
	
	
	// TODO NOT GREAT TO PUT THESE HERE, WRITE A SERVICE TIER FOR THIS.......
	@Autowired
	private UserRepository userRepository;
	
	
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
    		model.addAttribute("contactDetails", contactDetails);
    		return new ModelAndView("contact-details");
    	}
		
		logger.info("persisting contact details as part of this order process = " + contactDetails.toString());
		contactDetailsRepository.save(contactDetails);
		
		Shopping shopping = (Shopping)request.getSession().getAttribute("shopping");
		
		// the user is creating an order if following the standard journey, so at this stage create a new order object
		//Order order = shopping.getOrder() == null ? new Order(shopping.getUser(), shopping.getBasket()) : shopping.getOrder(); 
		Order order = shopping.getOrder() == null ? new Order(shopping.getUser(), shopping.getBasket()) : shopping.getOrder();		
//		Order order = shopping.getOrder();
//		if (shopping.getOrder() == null) {
//			order = new Order();
//			orderRepository.save(order);
//		}
		
		shopping.setOrder(order);
		PaymentInfo paymentInfo = new PaymentInfo();
		paymentInfo.setContactDetails(contactDetails);
		
		// persist payment info
		paymentInfoRepository.save(paymentInfo);
		
		// persist order with the payment info (contact details part)		
		order.setPaymentInfo(paymentInfo);
		order.setBasket(shopping.getBasket());
		order.setUser(shopping.getUser());
		// TODO orderRepository.save(order);
		
		// go to checkout page
		model.addAttribute("paymentInfo", new PaymentInfo());
		return new ModelAndView("checkout");
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
		paymentInfoRepository.save(shopPayInfo);
		logger.info("persisted payment info in full: " + paymentInfo.toString());
		
		//  TODO orderRepository.save(shopping.getOrder());
		
		if (purchase(shopping.getOrder())) {
			Purchase purchase = new Purchase(shopping.getUser(), LocalDate.now(), shopping.getOrder());
			shopping.setPurchase(purchase);
			// TODO persist the purchase value
			
			// TODO implement some tidying up:  basket.open = false; and similarly with all items
		} else {
			// TODO got to sale-failure with explanation, and a link to payment
		}
				
		return new ModelAndView("sale-confirmation");
	}
	
	
	
	// do something to actually purchase the 
	private boolean purchase(Order order) {
		return true;
	}
	
	
}
