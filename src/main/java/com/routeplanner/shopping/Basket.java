package com.routeplanner.shopping;

import java.util.Set;

public class Basket {

	private Set<AbstractItem> tickets;
	private User user;	
	
	public Basket(User user) {
		this.user = user;
	}
	
	public Basket(User user, Set<AbstractItem> tickets) {
		this(user);
		this.tickets = tickets;
	}


	public Set<AbstractItem> getTickets() {
		return tickets;
	}


	public void setTickets(Set<AbstractItem> tickets) {
		this.tickets = tickets;
	}

	
}
