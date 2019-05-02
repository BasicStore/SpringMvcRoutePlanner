package com.routeplanner.ctrl;
import java.util.HashSet;
import java.util.Set;

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
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@Autowired
	private ControllerHelper controllerHelper;
	
	@Autowired
	private TicketService ticketService;
	
	@Autowired
	private BasketService basketService;
	
	@Autowired
	private RouteQueryService routeQueryService;
	
	
	public BasketController() {
		
	}
	
	
	@PostMapping("/go-to-basket")
    public ModelAndView proceedToBasket(HttpServletRequest request, ModelMap model) {
		// display existing tickets in the basket
		Shopping shopping = (Shopping)request.getSession().getAttribute("shopping");
		Basket basket = (Basket)shopping.getBasket();
		basket.setTickets(new HashSet<Ticket>());
		return controllerHelper.prepareForViewBasket(request, model, basket);
	}
	
	
	@PostMapping("/remove_ticket")
	private ModelAndView deleteTicket(HttpServletRequest request, ModelMap model, @RequestParam int id){
	    ticketService.delete(id);
	    Shopping shopping = (Shopping)request.getSession().getAttribute("shopping");
		Basket basket = (Basket)shopping.getBasket();
		basket.removeTicket(id);
		return controllerHelper.prepareForViewBasket(request, model, basket);
	}
	
	
	
	@PostMapping("/add-ticket")
    public ModelAndView addTicket(HttpServletRequest request, ModelMap model, 
    		@Valid @ModelAttribute Ticket newTicket, BindingResult errors) {
		if (errors.hasErrors()) {
    		logger.info("errors exist on add ticket form on checkout page");
    		model.addAttribute("ticket", newTicket);
    		return new ModelAndView("checkout");
    	}
		
		ticketService.save(newTicket);
		
		// add the new ticket to the basket
		Shopping shopping = (Shopping)request.getSession().getAttribute("shopping");
		Basket basket = (Basket)shopping.getBasket();
		
		// add the ticket to the current shopping session variable
		if (basket.getTickets() == null) {
			basket.setTickets(new HashSet<Ticket>());
		}
		basket.getTickets().add(newTicket);
		
		RouteQuery routeQuery = (RouteQuery)request.getSession().getAttribute("mostRecentQuery");
		routeQueryService.save(routeQuery);
		newTicket.setRouteQuery(routeQuery);
		
		// prepare view existing tickets
		model.addAttribute("basket", basket);
		
		// prepare view to accept a new ticket		
		model.addAttribute("ticket", new Ticket()); 
    	
		ModelAndView mv = new ModelAndView("view-basket");
		return mv;
	}

	
}
