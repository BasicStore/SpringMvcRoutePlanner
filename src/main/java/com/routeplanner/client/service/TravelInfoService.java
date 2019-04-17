package com.routeplanner.client.service;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import com.routeplanner.dm.IRouteMap;
import com.routeplanner.dm.Journey;
import com.routeplanner.engine.IRoutePlanner;
import com.routeplanner.engine.RoutePlanner;
import com.routeplanner.ex.DuplicateStationException;
import com.routeplanner.ex.InvalidNetworkException;
import com.routeplanner.ex.InvalidStationException;
import com.routeplanner.ex.NoJourneyFoundException;
import com.routeplanner.load.RouteMapReader;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TravelInfoService implements ITravelInfoService {
	
	private final static Logger logger = LoggerFactory.getLogger(TravelInfoService.class);
	
	private final static ResourceBundle mybundle = ResourceBundle.getBundle("application");
	
	public TravelInfoService() {
		
	}
	
	
	public String getJourneyDetails(String start, String dest) {
		IRouteMap mapData;
		try {
			mapData = loadSystemData();   // TODO avoid doing this > 1 time!!!!!!!!
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
  
	
	
	
	// TODO avoid DUPLICATION!!!!
	
	public List<String> getStationList() {
		IRouteMap mapData;
		try {
			mapData = loadSystemData();
			RouteMapReader reader = new RouteMapReader();
			List<String> stationList = reader.getListAllStations(mapData);
			return stationList;
		} catch(FileNotFoundException e) {
			logger.info("File not found\n");
			logger.info("");
		} catch(IOException e) {
			logger.info("IOException \n");
			logger.info(e.getMessage());
		} catch(InvalidNetworkException e) {
			logger.info("Invalid Network\n");
			logger.info(e.getMessage());
		} 
		
		return new ArrayList<String>();
	}    
      
    
  	private IRouteMap loadSystemData() throws IOException, FileNotFoundException, InvalidNetworkException {
		RouteMapReader reader = new RouteMapReader();
		return reader.buildIRouteMap(mybundle.getString("route.map.xml"));
	}
	
}
