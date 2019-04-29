package com.routeplanner.ctrl;
import java.util.HashSet;
import java.util.Locale;
import java.util.ResourceBundle;

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

import com.routeplanner.shopping.Basket;
import com.routeplanner.shopping.RouteQuery;
import com.routeplanner.shopping.Shopping;
import com.routeplanner.shopping.Ticket;
import com.routeplanner.shopping.service.BasketService;
import com.routeplanner.shopping.service.RouteQueryService;
import com.routeplanner.shopping.service.TicketService;


@Controller
@RequestMapping("/routeplanner")
public class BasketController {

	private static final Logger logger = LoggerFactory.getLogger(BasketController.class);
	
	// TODO this must be dynamic
	final ResourceBundle prop = ResourceBundle.getBundle("messages", Locale.FRANCE);
	
	@Autowired
	private TicketService ticketService;
	
	@Autowired
	private BasketService basketService;
	
	@Autowired
	private RouteQueryService routeQueryService;
	
	public BasketController() {
		
	}

	
	// TODO change to POST
	@PostMapping("/go-to-basket")
    public ModelAndView proceedToBasket(HttpServletRequest request, ModelMap model) {
		// display existing tickets in the basket
		Shopping shopping = (Shopping)request.getSession().getAttribute("shopping");
		Basket basket = (Basket)shopping.getBasket();
		model.addAttribute("basket", basket);
    	
		// get the selected journey details
		RouteQuery mostRecentQuery = request.getSession().getAttribute("mostRecentQuery") == null ? null : (RouteQuery)request.getSession().getAttribute("mostRecentQuery");
		
		// do not proceed unless the journey details are provided
		if (mostRecentQuery == null || !mostRecentQuery.isSuccessfulLastSearch()) {
			model.addAttribute("routeQuery", new RouteQuery());
			model.addAttribute("errorLine1", prop.getString("rp.basket.no.route.err.msg.line1"));
			return new ModelAndView("query");
		}
		
		Ticket newTicket = new Ticket();
		newTicket.setRouteQuery(mostRecentQuery);
		model.addAttribute("ticket", newTicket);
		
		return new ModelAndView("view-basket");
	}

	
	
	
	
	
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
		
		// add the ticket to the current shopping session variable
		if (basket.getTickets() == null) {
			basket.setTickets(new HashSet<Ticket>());
		}
		basket.getTickets().add(newTicket);
		
		
		// TODO ************** RouteQuery routeQuery;
		RouteQuery routeQuery = (RouteQuery)request.getSession().getAttribute("mostRecentQuery");
		routeQueryService.save(routeQuery);
		newTicket.setRouteQuery(routeQuery);
		
		// persist the ticket and its basket now that there are some basket contents
		ticketService.save(newTicket);
		basketService.save(basket);
		logger.info("added new ticket to basket with id: " + newTicket.getId());
		
		// prepare view existing tickets
		model.addAttribute("basket", basket);
		
		// prepare view to accept a new ticket		
		model.addAttribute("ticket", new Ticket()); 
    	
		ModelAndView mv = new ModelAndView("view-basket");
		return mv;
	}

	
}
