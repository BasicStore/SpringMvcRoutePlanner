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
	//private String currentSelection; 
	//private Ticket currentSelection;
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
	
	
	
	

//	public String isCurrentSelection() {
//		return currentSelection;
//	}
//
//	public void setCurrentSelection(String currentSelection) {
//		this.currentSelection = currentSelection;
//	}
//
//	public Basket(Set<Ticket> tickets, User user, String currentSelection) {
//		super();
//		this.tickets = tickets;
//		this.user = user;
//		this.currentSelection = currentSelection;
//	}

	
	
	
	
//	
	public String getRadioButtonSelectedValue() {
		return radioButtonSelectedValue;
	}

	public void setRadioButtonSelectedValue(String radioButtonSelectedValue) {
		this.radioButtonSelectedValue = radioButtonSelectedValue;
	}

//	public Ticket getCurrentSelection() {
//		return currentSelection != null ? currentSelection : null;
//	}
////
//	public void setCurrentSelection(Ticket currentSelection) {
//		this.currentSelection = currentSelection;
//	}
//	
//	
//	
//	public void setCurrentSelection(String currentSelectionStr) {
//		this.currentSelection = currentSelection;
//	}
//	

	@Override
	public String toString() {
		return "Basket [tickets=" + tickets + ", user=" + user + ", currentSelection=" + radioButtonSelectedValue
				+ ", getTickets()=" + getTickets() + ", getUser()=" + getUser() + ", "
				+ ", getId()=" + getId() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	
	

}

