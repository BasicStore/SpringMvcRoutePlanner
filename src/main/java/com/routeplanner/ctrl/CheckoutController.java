package com.routeplanner.ctrl;
import java.util.Optional;

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
import com.routeplanner.shopping.RouteQuery;
import com.routeplanner.shopping.Shopping;
import com.routeplanner.shopping.User;


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

	@PostMapping("/proceed-to-checkout")
    public ModelAndView doCheckout(HttpServletRequest request, ModelMap model, @Valid @ModelAttribute RouteQuery routeQuery, BindingResult errors) {
		logger.info("proceeding to checkout");
		logger.info("current query start: " + routeQuery.getCurrRouteStart());
		logger.info("current query dest: " + routeQuery.getCurrRouteDest());
		logger.info("current query route info: " + routeQuery.getRouteInfo());
		
		// just collate the RouteQuery data and start a new abstract shopping object in the session with the data
		Shopping shopping = (Shopping)request.getSession().getAttribute("shopping");
		logger.info("Role level for this shopper: " + shopping.getUser().getRoleLevel().getLit());		
		
		
		
		ModelAndView mv = new ModelAndView("checkout");
		
		
		// if transaction failed: either go back to checkout or go to error page 
		
		
		
		
		return mv;
	}
	
	
	
	@PostMapping("/do-checkout")
	public ModelAndView doPurchase(HttpServletRequest request, ModelMap model, @Valid @ModelAttribute RouteQuery routeQuery, BindingResult errors) {
		
		// 	collects the payment detials for the basket which already exists
		
		// creates an order object and then places the order
		
		
		
		// if there are validation issues, go back to checkout		
		
		
		// else: DO THE PURCHASE NOW
		
		
	
		try {
			logger.info("about to save basket.........");
			Shopping shopping = (Shopping)request.getSession().getAttribute("shopping");
			
			// TODO  would normally get the user directly from the cart, but as it has not come from the db..... 			
//			Optional<User> user = userRepository.findByUsername("user1");
//			if (Optional.ofNullable(user).isPresent()) {
//				Basket basket = new Basket(user.get());
//				basketRepository.save(basket);
//				logger.info("basket is saved........");
//			}
			
			
			Basket basket = new Basket(shopping.getUser());
			basketRepository.save(basket);
			logger.info("basket is saved........");
			
			
		} catch(Exception e) {
			logger.info("Error saving basket: " + e.getMessage());
		}
		
	
	
	
		
		
		
		
		// if transaction successful, delete the contents from the basket WITHIN THE SAME TRANCSACTION, and go to sale_confirmation  
		ModelAndView mv = new ModelAndView("sale-confirmation");
		
		return mv;
	}
	
	
}
