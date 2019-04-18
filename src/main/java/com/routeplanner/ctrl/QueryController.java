package com.routeplanner.ctrl;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.apache.commons.lang3.StringUtils;
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
import com.routeplanner.client.service.TravelInfoService;
import com.routeplanner.dm.IRouteMap;
import com.routeplanner.dm.Journey;
import com.routeplanner.engine.IRoutePlanner;
import com.routeplanner.engine.RoutePlanner;
import com.routeplanner.ex.DuplicateStationException;
import com.routeplanner.ex.InvalidNetworkException;
import com.routeplanner.ex.InvalidStationException;
import com.routeplanner.ex.NoJourneyFoundException;
import com.routeplanner.load.RouteMapReader;
import com.routeplanner.shopping.RouteQuery;
import com.routeplanner.shopping.User;

@Controller
@RequestMapping("/routeplanner")
public class QueryController {
	
	private static final Logger logger = LoggerFactory.getLogger(QueryController.class);
	
	@Autowired
	private TravelInfoService travelInfoService;
		
	
	public QueryController() {
	
	}

	
	@PostMapping("/query")
    public ModelAndView findTravelInfo(HttpServletRequest request, ModelMap model, 
    		@Valid @ModelAttribute RouteQuery routeQuery, BindingResult errors) {
		String start = routeQuery.getCurrRouteStart();
		String dest = routeQuery.getCurrRouteDest();
		logger.info("Finding requested travel info. Start: " + start + "  Destination: " + dest);
		
		// find the travel info
        String routeInfo = StringUtils.isBlank(start) || StringUtils.isBlank(dest) 
				 ? "No travel data found" : travelInfoService.getJourneyDetails(start, dest);   
    	routeQuery.setRouteInfo(routeInfo);
    	model.addAttribute("routeQuery", routeQuery);
    	logger.info("current query route info: " + routeQuery.getRouteInfo());    	
    	
    	// add (the most recent) route query into a session     
    	request.getSession().setAttribute("mostRecentQuery", routeQuery);
    	
    	// go to query form and populate travel info
		ModelAndView mv = new ModelAndView("query");
		return mv;
	}
	
}
