package com.routeplanner.shopping;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;


@Entity
@Table(name="route_query")
public class RouteQuery extends DataModel 
{
	private String currRouteStart;
	
	private String currRouteDest;
	
	
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
	
	
//	public void setStartAndDestinationLabel() {
//		startAndDestinationLabel = (StringUtils.isBlank(currRouteStart) || StringUtils.isBlank(currRouteDest)) ? StringUtils.EMPTY : currRouteStart + " - " + currRouteDest;   
//	}
//	
//	
//	public String getStartAndDestinationLabel() {
//		setStartAndDestinationLabel();
//		return startAndDestinationLabel;
//	}
	

	@Override
	public String toString() {
		return "RouteQuery [currRouteStart=" + currRouteStart + ", currRouteDest=" + currRouteDest + ", routeInfo="
				+ routeInfo + ", getCurrRouteStart()=" + getCurrRouteStart() + ", getCurrRouteDest()="
				+ getCurrRouteDest() + ", getRouteInfo()=" + getRouteInfo() + ", getId()=" + getId() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
}
