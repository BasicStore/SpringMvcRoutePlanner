package com.routeplanner.dm;


public class JourneySummary {

	private final String currRouteStart;
	
	private final String currRouteDest;
	
	private final boolean successfulLastSearch;
	
	private final Exception failureException;
	
	private final String routeInfo;
	
	
	public JourneySummary(String currRouteStart, String currRouteDest, boolean successfulLastSearch, Exception failureException,
			String routeInfo) {
		super();
		this.currRouteStart = currRouteStart;
		this.currRouteDest = currRouteDest;
		this.successfulLastSearch = successfulLastSearch;
		this.failureException = failureException;
		this.routeInfo = routeInfo;
	}


	public String getCurrRouteStart() {
		return currRouteStart;
	}


	public String getCurrRouteDest() {
		return currRouteDest;
	}


	public boolean isSuccessfulLastSearch() {
		return successfulLastSearch;
	}
	

	public Exception getFailureException() {
		return failureException;
	}


	public String getRouteInfo() {
		return routeInfo;
	}

}
