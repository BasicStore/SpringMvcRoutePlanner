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
	
	
}	
