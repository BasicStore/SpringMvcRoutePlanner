package com.routeplanner.shopping;

import java.util.Set;

public class Basket extends AbstractShopping {

	private Set<AbstractItem> tickets;
		
	public Basket() {
	
		
	}
	
	public Basket(Set<AbstractItem> tickets) {
		this.tickets = tickets;
	}


	public Set<AbstractItem> getTickets() {
		return tickets;
	}


	public void setTickets(Set<AbstractItem> tickets) {
		this.tickets = tickets;
	}

	
}
