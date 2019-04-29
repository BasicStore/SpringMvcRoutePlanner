package com.routeplanner.client.service;
import java.util.List;
import com.routeplanner.dm.JourneySummary;

public interface ITravelInfoService {
	
	List<String> getStationList();
	
	JourneySummary getJourneyDetails(String start, String dest);
	
}
