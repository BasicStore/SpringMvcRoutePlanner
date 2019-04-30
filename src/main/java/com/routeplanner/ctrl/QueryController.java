package com.routeplanner.ctrl;
import java.io.FileNotFoundException;
import java.io.IOException;
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
import com.routeplanner.client.service.TravelInfoService;
import com.routeplanner.dm.JourneySummary;
import com.routeplanner.ex.DuplicateStationException;
import com.routeplanner.ex.InvalidNetworkException;
import com.routeplanner.ex.InvalidStationException;
import com.routeplanner.ex.NoJourneyFoundException;
import com.routeplanner.shopping.RouteQuery;

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
        JourneySummary journeySummary = travelInfoService.getJourneyDetails(start, dest); 
        if (journeySummary.isSuccessfulLastSearch()) {
        	routeQuery.setRouteInfo(journeySummary.getRouteInfo());
        	routeQuery.setSuccessfulLastSearch(true);
        	logger.info("current query route info: " + routeQuery.getRouteInfo());
        } else { // something went wrong in the model
        	routeQuery.setSuccessfulLastSearch(false);
        	if (journeySummary.getFailureException() instanceof IOException) {
        		routeQuery.setRouteInfo("error.engine.io");
        	} else if (journeySummary.getFailureException() instanceof FileNotFoundException) {
        		routeQuery.setRouteInfo("error.engine.file.not.found");
			} else if (journeySummary.getFailureException() instanceof InvalidStationException) {
				routeQuery.setRouteInfo("error.engine.invalid.station");	
			} else if (journeySummary.getFailureException() instanceof InvalidNetworkException) {
				routeQuery.setRouteInfo("error.engine.invalid.network");
			} else if (journeySummary.getFailureException() instanceof NoJourneyFoundException) {
				routeQuery.setRouteInfo("error.engine.no.journey.found");
			} else if (journeySummary.getFailureException() instanceof DuplicateStationException) {
				routeQuery.setRouteInfo("error.engine.start.and.dest.the.same");
			} else if (journeySummary.getFailureException() instanceof Exception) {
				routeQuery.setRouteInfo("error.engine.generic");
			}
        	model.addAttribute("errorLine1", "rp.basket.no.route.err.msg.line1");
        }
        
    	model.addAttribute("routeQuery", routeQuery);
    	
    	// add (the most recent) route query into a session     
    	request.getSession().setAttribute("mostRecentQuery", routeQuery);
    	
    	// go to query form and populate travel info
		ModelAndView mv = new ModelAndView("query");
		return mv;
	}
	
}
