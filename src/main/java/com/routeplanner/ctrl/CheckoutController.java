package com.routeplanner.ctrl;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.routeplanner.shopping.RouteQuery;
import com.routeplanner.shopping.Shopping;


@Controller
@RequestMapping("/routeplanner")
public class CheckoutController {

	private static final Logger logger = LoggerFactory.getLogger(CheckoutController.class);
	
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
	public ModelAndView doPurchase() {
		
		// 	collects the payment detials for the basket which already exists
		
		// creates an order object and then places the order
		
		
		
		// if there are validation issues, go back to checkout		
		
		
		// else: DO THE PURCHASE NOW
		
		// if transaction successful, delete the contents from the basket WITHIN THE SAME TRANCSACTION, and go to sale_confirmation  
		ModelAndView mv = new ModelAndView("sale-confirmation");
		
		return mv;
	}
	
	
}
