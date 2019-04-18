package com.routeplanner.shopping;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="ticket")
public class Ticket extends AbstractItem {
	
	@OneToOne
	private PassengerType passengerType;
	
	private LocalDateTime travelDate;
	
	private TicketType ticketType;
		
	@OneToOne
	private RouteQuery routeQuery;
	
	@OneToOne
	private Rule rule;
	
	
	public Ticket() {
		
	}


	public Ticket(boolean open, int numUnits, PassengerType passengerType, LocalDateTime travelDate, 
			RouteQuery routeQuery, Rule rule) {
		super(open, numUnits);
		this.passengerType = passengerType;
		this.travelDate = travelDate;
		this.routeQuery = routeQuery;
		this.rule = rule;
	}


	public PassengerType getPassengerType() {
		return passengerType;
	}


	public void setPassengerType(PassengerType passengerType) {
		this.passengerType = passengerType;
	}


	public LocalDateTime getTravelDate() {
		return travelDate;
	}


	public void setTravelDate(LocalDateTime travelDate) {
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
	
}	
