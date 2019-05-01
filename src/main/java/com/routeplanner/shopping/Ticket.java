package com.routeplanner.shopping;
import java.time.LocalDate;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.routeplanner.shopping.converters.PassengerTypeConverter;
import com.routeplanner.shopping.converters.TicketTypeConverter;

@Entity
@Table(name="ticket")
public class Ticket extends AbstractItem {
	
	@Convert(converter = PassengerTypeConverter.class)
	private PassengerType passengerType;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate travelDate;
	
	@Convert(converter = TicketTypeConverter.class)
	private TicketType ticketType;
		
	@OneToOne
	private RouteQuery routeQuery;
	
	@OneToOne
	private Rule rule;
	
	
	public Ticket() {
		
	}

	public Ticket(boolean open, int numUnits, PassengerType passengerType, LocalDate travelDate, 
			RouteQuery routeQuery, Rule rule) {
		super(numUnits);
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
				+ ", routeQuery=" + routeQuery + ", rule=" + rule + ", id=" + id + ", getPassengerType()="
				+ getPassengerType() + ", getTravelDate()=" + getTravelDate() + ", getTicketType()=" + getTicketType()
				+ ", getRouteQuery()=" + getRouteQuery() + ", getRule()=" + getRule() 
				+ ", getNumUnits()=" + getNumUnits() + ", toString()=" + super.toString() + ", getId()=" + getId()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}
	
}	
