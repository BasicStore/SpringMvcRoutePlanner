package com.routeplanner.shopping;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="basket")
public class Basket extends DataModel {

	@OneToMany
	private Set<Ticket> tickets;
	
	@OneToOne
	private User user;	
	
	@Transient
	private String radioButtonSelectedValue;
	
	public Basket() {

	}
	
	public Basket(User user) {
		this.user = user;
	}
	

	public Basket(User user, Set<Ticket> tickets) {
		this(user);
		this.tickets = tickets;
	}

	public Set<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(Set<Ticket> tickets) {
		this.tickets = tickets;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	public String getRadioButtonSelectedValue() {
		return radioButtonSelectedValue;
	}

	public void setRadioButtonSelectedValue(String radioButtonSelectedValue) {
		this.radioButtonSelectedValue = radioButtonSelectedValue;
	}

	@Override
	public String toString() {
		return "Basket [tickets=" + tickets + ", user=" + user + ", currentSelection=" + radioButtonSelectedValue
				+ ", getTickets()=" + getTickets() + ", getUser()=" + getUser() + ", "
				+ ", getId()=" + getId() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
	

}

