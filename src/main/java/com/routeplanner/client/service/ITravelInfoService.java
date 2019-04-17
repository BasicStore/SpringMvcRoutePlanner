package com.routeplanner.client.service;
import java.util.List;

public interface ITravelInfoService {
	
	List<String> getStationList();
	
	String getJourneyDetails(String start, String dest);
	
}
