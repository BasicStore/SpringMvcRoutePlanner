package com.routeplanner.shopping;
import java.time.LocalDateTime;


public class Ticket extends AbstractItem {
	
	// TODO reconsider.......
	private Ticket ticket;
		
	private PassengerType passengerType;
	
	// TODO maybe Date would be better........
	private LocalDateTime travelDate;
	
	private RouteQuery routeQuery;
	
	private Rule rule;
	
	
	public Ticket() {
		
	}


	public Ticket(Ticket ticket, PassengerType passengerType, LocalDateTime travelDate, RouteQuery routeQuery,
			Rule rule) {
		this.ticket = ticket;
		this.passengerType = passengerType;
		this.travelDate = travelDate;
		this.routeQuery = routeQuery;
		this.rule = rule;
	}
	

	
}	