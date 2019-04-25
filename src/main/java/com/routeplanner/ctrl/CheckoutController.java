package com.routeplanner.ctrl;
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
import com.routeplanner.repository.OrderRepository;
import com.routeplanner.repository.PaymentInfoRepository;
import com.routeplanner.repository.UserRepository;
import com.routeplanner.shopping.Basket;
import com.routeplanner.shopping.ContactDetails;
import com.routeplanner.shopping.PaymentInfo;
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
	
	@Autowired
	private OrderRepository orderRepository;
	
	
	// TODO NOT GREAT TO PUT THESE HERE, WRITE A SERVICE TIER FOR THIS.......
	@Autowired
	private UserRepository userRepository;
	
	
	public CheckoutController() {
		
	}

	
	@PostMapping("/go-to-checkout")
	public ModelAndView goToCheckout(HttpServletRequest request, ModelMap model, @Valid @ModelAttribute Basket basket, BindingResult errors) {
		if (errors.hasErrors()) {
    		logger.info("errors exist on submitBasketForm on view basket page");
    		model.addAttribute("basket", basket);
    		model.addAttribute("ticket", new Ticket());
    		return new ModelAndView("view-basket");
    	}
		
		logger.info("go to checkout from basket page. BASKet = " + basket.toString());
		
		// prepare new payment info and new contact details parts of the screen
		model.addAttribute("paymentInfo", new PaymentInfo());	
		model.addAttribute("contactDetails", new ContactDetails());
		
		return new ModelAndView("checkout");
	}
	
	
	
	@PostMapping("/add-paymentinfo")
	public ModelAndView addPaymentInfo(HttpServletRequest request, ModelMap model, @Valid @ModelAttribute PaymentInfo paymentInfo, BindingResult errors) {
		Shopping shopping = (Shopping)request.getSession().getAttribute("shopping");
		if (errors.hasErrors()) {
    		logger.info("errors exist on paymentDetailsForm on checkout page");
    		model.addAttribute("paymentInfo", paymentInfo);
    		return new ModelAndView("checkout");
    	}
		
		// persist the payment info
		paymentInfoRepository.save(paymentInfo);
		
		// prepare new payment info part of screen
		model.addAttribute("paymentInfo", new PaymentInfo());
		model.addAttribute("contactDetails", new ContactDetails());
		
		
		
		
		
		return new ModelAndView("checkout");
	}
	
	
	
	// TOOD IN PROGRESS
	@PostMapping("/add-contact-details")
	public ModelAndView addContactDetails(HttpServletRequest request, ModelMap model, @Valid @ModelAttribute PaymentInfo paymentInfo, BindingResult errors) {
//		Shopping shopping = (Shopping)request.getSession().getAttribute("shopping");
//		if (errors.hasErrors()) {
//    		logger.info("errors exist on paymentDetailsForm on checkout page");
//    		model.addAttribute("paymentInfo", paymentInfo);
//    		return new ModelAndView("checkout");
//    	}
//		
//		// persist the payment info
//		paymentInfoRepository.save(paymentInfo);
//		
//		// prepare new payment info part of screen
//		model.addAttribute("paymentInfo", new PaymentInfo());
		
		
		return new ModelAndView("checkout");
	}
	
	
	
	
	
	
	@PostMapping("/do-purchase")
	public ModelAndView doPurchase(HttpServletRequest request, ModelMap model, @Valid @ModelAttribute PaymentInfo paymentInfo, BindingResult errors) {
		Shopping shopping = (Shopping)request.getSession().getAttribute("shopping");
		if (errors.hasErrors()) {
    		logger.info("errors exist on purchaseForm on checkout page");
    		model.addAttribute("basket", shopping.getBasket());
    		return new ModelAndView("checkout");
    	}
		
		
		// order.setPaymentInfo(paymentInfo);
		
		
		// get or create the order for this shopping spree
//				Order order = shopping.getOrder() == null ? new Order(shopping.getUser(), shopping.getBasket()) : shopping.getOrder();
//				orderRepository.save(order);
		
		
		
		
		
		// DO THE PURCHASE NOW
//		try {
//			logger.info("about to save basket.........");
//			Shopping shopping = (Shopping)request.getSession().getAttribute("shopping");
//			Basket basket = new Basket(shopping.getUser());
//			basketRepository.save(basket);
//			logger.info("basket is saved........");
//			
//			
//		} catch(Exception e) {
//			logger.info("Error saving basket: " + e.getMessage());
//		}
		
		
		// if transaction successful, delete the contents from the basket WITHIN THE SAME TRANCSACTION, and go to sale_confirmation  
		ModelAndView mv = new ModelAndView("sale-confirmation");
		
		return mv;
	}
	
	
}
