package com.routeplanner.shopping;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="basket")
public class Basket extends DataModel {

	@OneToMany
	private Set<Ticket> tickets;
	
	@OneToOne
	private User user;	
	
	public Basket() {

	}
	
	public Basket(User user) {
		this.user = user;
	}
	

	public Basket(User user, Set<Ticket> tickets) {
		this(user);
		this.tickets = tickets;
	}
	
}

