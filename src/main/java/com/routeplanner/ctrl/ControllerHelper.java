package com.routeplanner.ctrl;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import com.routeplanner.shopping.Basket;
import com.routeplanner.shopping.RouteQuery;
import com.routeplanner.shopping.Ticket;


@Component
public class ControllerHelper {

	protected ModelAndView prepareForViewBasket(HttpServletRequest request, ModelMap model, Basket basket) {
		model.addAttribute("basket", basket);
		
		// get the selected journey details
		RouteQuery mostRecentQuery = request.getSession().getAttribute("mostRecentQuery") == null 
				? null : (RouteQuery)request.getSession().getAttribute("mostRecentQuery");
		
		// do not proceed unless the journey details are provided
		if (mostRecentQuery == null || !mostRecentQuery.isSuccessfulLastSearch()) {
			model.addAttribute("routeQuery", new RouteQuery());
			model.addAttribute("errorLine1", "rp.basket.no.route.err.msg.line1");
			return new ModelAndView("query");
		}
		
		// prepare for possibility of user adding a new ticket
		Ticket newTicket = new Ticket();
		newTicket.setRouteQuery(mostRecentQuery);
		model.addAttribute("ticket", newTicket);
		
		return new ModelAndView("view-basket");
	}
	
	
}
