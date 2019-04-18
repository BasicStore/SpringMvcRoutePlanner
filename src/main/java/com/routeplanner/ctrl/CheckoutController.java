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
import com.routeplanner.repository.UserRepository;
import com.routeplanner.shopping.Basket;
import com.routeplanner.shopping.Order;
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
	
	// TODO NOT GREAT TO PUT THESE HERE, WRITE A SERVICE TIER FOR THIS.......
	@Autowired
	private UserRepository userRepository;
	
	
	public CheckoutController() {
		
	}

		
	
	
	@PostMapping("/go-to-checkout")
	public ModelAndView goToPayment(HttpServletRequest request, ModelMap model, @Valid @ModelAttribute Basket basket, BindingResult errors) {
		if (errors.hasErrors()) {
    		logger.info("errors exist on submitBasketForm on view basket page");
    		model.addAttribute("basket", basket);
    		model.addAttribute("ticket", new Ticket());
    		return new ModelAndView("view-basket");
    	}
		
		// create an order with this basket and put this in the session
		Shopping shopping = (Shopping)request.getSession().getAttribute("shopping");
		Order order = new Order();
		order.setBasket(basket);
		order.setUser(shopping.getUser());
		
		// prepare new payment info part of screen
		model.addAttribute("paymentInfo", new PaymentInfo());		
		
		return new ModelAndView("checkout");
	}
	
	
	
	
	@PostMapping("/do-purchase")
	public ModelAndView doPurchase(HttpServletRequest request, ModelMap model, @Valid @ModelAttribute PaymentInfo paymentInfo, BindingResult errors) {
		Shopping shopping = (Shopping)request.getSession().getAttribute("shopping");
		if (errors.hasErrors()) {
    		logger.info("errors exist on checkout page");
    		model.addAttribute("basket", shopping.getBasket());
    		return new ModelAndView("checkout");
    	}
		
		// update the payment info for the current order
		Order order = shopping.getOrder();
		order.setPaymentInfo(paymentInfo);
		
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
