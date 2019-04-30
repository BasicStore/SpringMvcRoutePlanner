package com.routeplanner.shopping;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="purchase")
public class Purchase extends DataModel {
	
	@OneToOne
	private User user;
	
	@Column(name="transaction")
	private LocalDate transactionDate;  
	
	@OneToOne
	private Order order;
	
	public Purchase() {
		
	}
		
	public Purchase(User user, LocalDate transactionDate, Order order) {
		this.user = user;
		this.transactionDate = transactionDate;
		this.order = order;
	}

	public LocalDate getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "Purchase [user=" + user + ", transactionDate=" + transactionDate + ", order=" + order
				+ ", getTransactionDate()=" + getTransactionDate() + ", getId()=" + getId() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
}
