package com.routeplanner.ctrl;
import java.util.HashSet;
import java.util.Locale;
import java.util.ResourceBundle;

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

import com.routeplanner.shopping.Basket;
import com.routeplanner.shopping.PassengerType;
import com.routeplanner.shopping.RouteQuery;
import com.routeplanner.shopping.Shopping;
import com.routeplanner.shopping.Ticket;
import com.routeplanner.shopping.TicketType;


@Controller
@RequestMapping("/routeplanner")
public class BasketController {

	private static final Logger logger = LoggerFactory.getLogger(BasketController.class);
	
	// TODO FIND A DYNAMIC WAY OF ACHIEVING THIS..........
	final static ResourceBundle prop = ResourceBundle.getBundle("messages", Locale.FRANCE);
	
	
	public BasketController() {
		
	}

	
	// TODO change to POST
	@PostMapping("/go-to-basket")
    public ModelAndView proceedToBasket(HttpServletRequest request, ModelMap model) {
		
		// display existing tickets in the basket
		Shopping shopping = (Shopping)request.getSession().getAttribute("shopping");
		Basket basket = (Basket)shopping.getBasket();
		model.addAttribute("basket", basket);
    	
		// create a NEW ticket based on the most recent travel query, and add to MODEL 
		RouteQuery mostRecentQuery = (RouteQuery)request.getSession().getAttribute("mostRecentQuery");
		Ticket newTicket = new Ticket();
		newTicket.setRouteQuery(mostRecentQuery);
		model.addAttribute("ticket", newTicket);
		
		// add static vars to model to assist creating a new ticket
		addStaticSessVars(request, model);
		
		return new ModelAndView("view-basket");
	}

	
	
	// TODO *****************************************************
	@PostMapping("/add-ticket")
    public ModelAndView addTicket(HttpServletRequest request, ModelMap model, 
    		@Valid @ModelAttribute Ticket newTicket, BindingResult errors) {
		if (errors.hasErrors()) {
    		logger.info("errors exist on add ticket form on checkout page");
    		model.addAttribute("ticket", newTicket);
    		return new ModelAndView("checkout");
    	}

		
		
		// add the new ticket to the basket
		Shopping shopping = (Shopping)request.getSession().getAttribute("shopping");
		Basket basket = (Basket)shopping.getBasket();
		basket.setUser(shopping.getUser());
		if (basket.getTickets() == null) {
			basket.setTickets(new HashSet<Ticket>());
		}
		basket.getTickets().add(newTicket);
		logger.info("added ticket to basket: " + newTicket.toString());
		
		// TODO at this stage save the basket to the database?
		
		// prepare for existing tickets
		model.addAttribute("basket", basket);
		
		// prepare for new ticket		
		model.addAttribute("ticket", new Ticket()); 
		addStaticSessVars(request, model);
    	
		ModelAndView mv = new ModelAndView("view-basket");
		return mv;
	}

	
	
	
	
	
	// TODO should not be necessary!!!! omit this
	// TODO needs to be refactored so that view basket uses session values directly for this static content
	private void addStaticSessVars(HttpServletRequest request, ModelMap model) {
		model.addAttribute("ticketTypeList", TicketType.values());
		
		
		
		//logger.info("ACCESS INTERNATIONALIZATION FROM JAVA TEST: " + prop.getString("test"));
		
		//Locale.getDefault();
		
		
		//Collection<PassengerTypeOLD> pasTypes = (Collection<PassengerTypeOLD>)request.getSession().getAttribute("passengerTypeList");
    	//pasTypes.forEach(t->logger.info("CC passenger type code from db = " + t.getCode()));

		model.addAttribute("passengerTypeList", PassengerType.values());
	}
	
	
}
