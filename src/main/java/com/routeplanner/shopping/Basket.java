package com.routeplanner.shopping;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.routeplanner.dm.DataModel;


@Entity
@Table(name="basket")
public class Basket extends DataModel {

	@OneToMany
	private Set<Ticket> tickets;
	
	@OneToOne
	private User user;	
	
	@Transient
	private String radioButtonSelectedValue;
	
	// TODO should this be explicit but have duplication????? or leave  program to checlk whether there are any open items
	private boolean open = true;
	
	
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

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	@Override
	public String toString() {
		return "Basket [tickets=" + tickets + ", user=" + user + ", radioButtonSelectedValue="
				+ radioButtonSelectedValue + ", open=" + open + ", id=" + id + ", getTickets()=" + getTickets()
				+ ", getUser()=" + getUser() + ", getRadioButtonSelectedValue()=" + getRadioButtonSelectedValue()
				+ ", isOpen()=" + isOpen() + ", getId()=" + getId() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

}

