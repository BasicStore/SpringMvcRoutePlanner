package com.routeplanner.ctrl;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
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

@Controller
@RequestMapping("/routeplanner")
public class QueryController {
	
	private Logger logger = LoggerFactory.getLogger(QueryController.class);
	
	
	// TODO move this with the route engine
	final static ResourceBundle mybundle = ResourceBundle.getBundle("application");
	
	public QueryController() {
	
	}

	
	@PostMapping("/query")
    public ModelAndView greetingSubmit(HttpServletRequest request, ModelMap model, 
    		@Valid @ModelAttribute RouteQuery routeQuery, BindingResult errors) {
		
		String start = routeQuery.getCurrRouteStart();
		String dest = routeQuery.getCurrRouteDest();
		logger.info("start = " + start);
		logger.info("destination = " + dest);
		
        String routeInfo = StringUtils.isBlank(start) || StringUtils.isBlank(dest) 
				 ? StringUtils.EMPTY : getJourneyDetails(start, dest);   
    	routeQuery.setRouteInfo(routeInfo);
    	model.addAttribute("routeQuery", routeQuery);
    	
		ModelAndView mv = new ModelAndView("query");
		return mv;
	}
	
	
	
	
	
	
	////////////////////////////////////////////////////////////////////////////////////////
	// TODO MOVE THIS OUT OF THE CONTROLLER, OF COURSE, AND REFACTOR FOR EFFICIENCY
	//      many change necessary here!!!!  POC ONLY
	public String getJourneyDetails(String start, String dest) {
		IRouteMap mapData;
		try {
			mapData = loadSystemData();
			IRoutePlanner planner = new RoutePlanner(mapData); 
			String[] routeDetails = new String[] {start, dest};
			Journey journey = planner.lookupJourney(routeDetails[0], routeDetails[1]);
			String journeyDisplay = planner.getJourneyString(journey);
			logger.info("Journey Display:  " + journeyDisplay);
			return journeyDisplay;
		} catch(FileNotFoundException e) {
			logger.info("File not found\n");
			logger.info("");
		} catch(IOException e) {
			logger.info("IOException \n");
			logger.info(e.getMessage());
		} catch(InvalidStationException e) {
			logger.info("Invalid Station\n");
			logger.info(e.getMessage());
		} catch(InvalidNetworkException e) {
			logger.info("Invalid Network\n");
			logger.info(e.getMessage());
		} catch(NoJourneyFoundException e) { 
			logger.info("No Journey Found\n");
			logger.info(e.getMessage());
		} catch (DuplicateStationException e) {
			logger.info("Duplicate Station entered - presumably start and destination are the same\n");
			logger.info(e.getMessage());
		}
		
		return null;
	}    
  
    
  
    // TODO consider moving this.......
  	public IRouteMap loadSystemData() throws IOException, FileNotFoundException, InvalidNetworkException {
		RouteMapReader reader = new RouteMapReader();
		return reader.buildIRouteMap(mybundle.getString("route.map.xml"));
	}
	
	
	
}
