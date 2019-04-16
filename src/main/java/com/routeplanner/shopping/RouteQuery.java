package com.routeplanner.shopping;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;


public class RouteQuery 
{
	private String currRouteStart;
	private String currRouteDest;
	private String routeInfo;
	
	private List<Integer> zonesCovered;
	
	
	public RouteQuery() {
		
	}


	public String getCurrRouteStart() {
		return currRouteStart;
	}


	public void setCurrRouteStart(String currRouteStart) {
		this.currRouteStart = currRouteStart;
	}


	public String getCurrRouteDest() {
		return currRouteDest;
	}


	public void setCurrRouteDest(String currRouteDest) {
		this.currRouteDest = currRouteDest;
	}


	public String getRouteInfo() {
		return routeInfo;
	}


	public void setRouteInfo(String routeInfo) {
		this.routeInfo = routeInfo;
	}
	
		
}
