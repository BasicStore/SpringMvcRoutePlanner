package com.routeplanner.shopping;
import java.sql.Blob;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import org.hibernate.Hibernate;


@Entity
@Table(name="route_query")
public class RouteQuery extends DataModel 
{
//	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO)
//	@Column(name="rqid")
//	private int id;
	
	private String currRouteStart;
	
	private String currRouteDest;
	
//	@Lob
//    @Column(name = "route_info", columnDefinition = "LONGBLOB")
//	private Blob routeInfo;
	
	
	// TODO needs saving as Blob........
	private String routeInfo;
	
	
	// for simplicity, implement without this first
	//private List<Integer> zonesCovered;
	
	
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
		
		// this.routeInfo = Hibernate.createBlob(routeInfo.getBytes());
		
	}


	@Override
	public String toString() {
		return "RouteQuery [currRouteStart=" + currRouteStart + ", currRouteDest=" + currRouteDest + ", routeInfo="
				+ routeInfo + ", getCurrRouteStart()=" + getCurrRouteStart() + ", getCurrRouteDest()="
				+ getCurrRouteDest() + ", getRouteInfo()=" + getRouteInfo() + ", getId()=" + getId() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
}
