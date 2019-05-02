package com.routeplanner.shopping;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;


@Entity
@Table(name="route_query")
public class RouteQuery extends DataModel 
{
	@Column(length = 45)
	private String currRouteStart;
	
	@Column(length = 45)
	private String currRouteDest;
	
	// a route query is tentatively successful until it fails
	private boolean successfulLastSearch = true;
	
//	@Lob
//    @Column(name = "route_info", columnDefinition = "LONGBLOB")
//	private Blob routeInfo;
	// TODO needs saving as Blob........this is a temporary measure....
	@Column(name = "route_info", nullable = false, length = 1000)  
	@Length(max = 2000)
	private String routeInfo;
	
	// TODO  for simplicity, implement without this first
	//private List<Integer> zonesCovered;
	
	public RouteQuery() {
		
	}


	public RouteQuery(String currRouteStart, String currRouteDest, String routeInfo) {
		this.currRouteStart = currRouteStart;
		this.currRouteDest = currRouteDest;
		this.routeInfo = routeInfo;
	}


	public RouteQuery(String currRouteStart, String currRouteDest, String routeInfo, boolean successfulLastSearch) {
		this(currRouteStart, currRouteDest, routeInfo);
		this.successfulLastSearch=successfulLastSearch; 
	}
	
	
	public String getCurrRouteStart() {
		return currRouteStart;
	}


	public void setCurrRouteStart(String currRouteStart) {
		this.currRouteStart = currRouteStart;
		//setStartAndDestinationLabel();
	}


	public String getCurrRouteDest() {
		return currRouteDest;
	}


	public void setCurrRouteDest(String currRouteDest) {
		this.currRouteDest = currRouteDest;
		//setStartAndDestinationLabel();
	}


	public String getRouteInfo() {
		return routeInfo;
	}


	public void setRouteInfo(String routeInfo) {
		this.routeInfo = routeInfo;
		
		// this.routeInfo = Hibernate.createBlob(routeInfo.getBytes());
		
	}
	
	
	public boolean isSuccessfulLastSearch() {
		return successfulLastSearch;
	}


	public void setSuccessfulLastSearch(boolean successfulLastSearch) {
		this.successfulLastSearch = successfulLastSearch;
	}


	@Override
	public String toString() {
		return "RouteQuery [currRouteStart=" + currRouteStart + ", currRouteDest=" + currRouteDest + ", routeInfo="
				+ routeInfo + ", getCurrRouteStart()=" + getCurrRouteStart() + ", getCurrRouteDest()="
				+ getCurrRouteDest() + ", getRouteInfo()=" + getRouteInfo() + ", getId()=" + getId() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
}
