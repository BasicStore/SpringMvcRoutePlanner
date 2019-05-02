package com.routeplanner.shopping;
import java.util.Optional;
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
	
	private boolean open;
	
	
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

	
	public void removeTicket(int id) {
		if (tickets != null) {
			Optional<Ticket> tickToRemove = tickets.stream().filter(o->o.getId() == id).findFirst();
			tickToRemove.ifPresent(t->tickets.remove(t)); 
		}
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
	
	/**
	 * Sets the status of the basket and its contents to open or closed.
	 * @param open
	 */
	public void setOpen(boolean open) {
		this.open = open;
	}
	
	public void closeAllTickets() {
		if (tickets != null) {
			tickets.forEach(i->i.setOpen(false));
		}
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

