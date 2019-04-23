package com.routeplanner.shopping;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;



@Entity
@Table(name="ticket")
public class Ticket extends AbstractItem {
	
	private static int counter = 0;
	
	
	private PassengerType passengerType;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate travelDate;
	
	private TicketType ticketType;
		
	@OneToOne
	private RouteQuery routeQuery;
	
	@OneToOne
	private Rule rule;
	
	
	public Ticket() {
		this.id = counter;// TODO POC ONLY!!
		counter++;
	}


	public Ticket(boolean open, int numUnits, PassengerType passengerType, LocalDate travelDate, 
			RouteQuery routeQuery, Rule rule) {
		super(open, numUnits);
		this.id = counter;
		this.passengerType = passengerType;
		this.travelDate = travelDate;
		this.routeQuery = routeQuery;
		this.rule = rule;
		counter++;
	}


	public PassengerType getPassengerType() {
		return passengerType;
	}


//	public void setPassengerType(String passTypeStr) {
//		
//		// PassengerType passengerType
//		
//		
//		this.passengerType = new PassengerType("AAAA", "BBBBB");
//	}

	
	public void setPassengerType(PassengerType passengerType) {
		this.passengerType = passengerType;
	}
		

	public LocalDate getTravelDate() {
		return travelDate;
	}


	public void setTravelDate(LocalDate travelDate) {
		this.travelDate = travelDate;
	}


	public TicketType getTicketType() {
		return ticketType;
	}


	public void setTicketType(TicketType ticketType) {
		this.ticketType = ticketType;
	}


	public RouteQuery getRouteQuery() {
		return routeQuery;
	}


	public void setRouteQuery(RouteQuery routeQuery) {
		this.routeQuery = routeQuery;
	}


	public Rule getRule() {
		return rule;
	}


	public void setRule(Rule rule) {
		this.rule = rule;
	}


	@Override
	public String toString() {
		return "Ticket [passengerType=" + passengerType + ", travelDate=" + travelDate + ", ticketType=" + ticketType
				+ ", routeQuery=" + routeQuery + ", rule=" + rule + ", getPassengerType()=" + getPassengerType()
				+ ", getTravelDate()=" + getTravelDate() + ", getTicketType()=" + getTicketType() + ", getRouteQuery()="
				+ getRouteQuery() + ", getRule()=" + getRule() + ", isOpen()=" + isOpen() + ", getNumUnits()="
				+ getNumUnits() + ", toString()=" + super.toString() + ", getId()=" + getId() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + "]";
	}
	
	
}	
