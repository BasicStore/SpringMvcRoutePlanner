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
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ContractDetailsRepository contactDetailsRepository;
	
	
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
		//model.addAttribute("paymentInfo", new PaymentInfo());	
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
		Order order = shopping.getOrder();
		PaymentInfo paymentInfo = new PaymentInfo();
		paymentInfo.setContactDetails(contactDetails);
		
		// persist payment info
		paymentInfoRepository.save(paymentInfo);
		
		// persist order with the payment info (contact details part)		
		order.setPaymentInfo(paymentInfo);
		
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
		
		// update the payment with the card details
		PaymentInfo shopPayInfo = shopping.getOrder().getPaymentInfo();
		shopPayInfo.setCardNumber(paymentInfo.getCardNumber());
		shopPayInfo.setCardType(paymentInfo.getCardType());		
		shopPayInfo.setExpiry_date(paymentInfo.getExpiry_date());
		shopPayInfo.setNameOnCard(paymentInfo.getNameOnCard());
		shopPayInfo.setSecurityCode(paymentInfo.getSecurityCode());
		
		// persist the payment info
		paymentInfoRepository.save(shopPayInfo);
		orderRepository.save(shopping.getOrder());
		
		// TODO if all successful then create a purchase here...........................and persist it
		Purchase purchase = new Purchase(shopping.getUser(), LocalDate.now(), shopping.getOrder());
		// TODO DO THE PURCHASE (PLACEHOLDER STUB)
		// TODO if successful PERSIST IT
		
		// TODO think about presentation of the model on the sale confirmation page 
				
		return new ModelAndView("sale-confirmation");
	}
	
	
}
