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
import com.routeplanner.shopping.User;


@Controller
@RequestMapping("/routeplanner")
public class CheckoutController {

	private Logger logger = LoggerFactory.getLogger(CheckoutController.class);
	
	public CheckoutController() {
		
	}

	
	@PostMapping("/checkout")
    public ModelAndView greetingSubmit(HttpServletRequest request, ModelMap model, @Valid @ModelAttribute User login, BindingResult errors) {
		
		
		// collects the payment detials for the basket which already exists
		
		// creates an order object and then places the order
		
		
		
		// if there are validation issues, go back to checkout		
		
		// if transaction successful, delete the contents from the basket WITHIN THE SAME TRANCSACTION, and go to sale_confirmation  
		
		// if transaction failed: either go back to checkout or go to error page 
		
		ModelAndView mv = new ModelAndView("sale-confirmation");
		return mv;
	}
	
	
	
}
