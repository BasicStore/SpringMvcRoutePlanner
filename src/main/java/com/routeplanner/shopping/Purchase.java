package com.routeplanner.shopping;

import java.util.Date;

public class Purchase extends Order {

	// TODO what is the best Date / LocalDateTime??????
	private Date transactionDate;
	
	private Order order;
	
	
	public Purchase() {
		
		
		
	}


	public Purchase(Date transactionDate, Order order) {
		this.transactionDate = transactionDate;
		this.order = order;
	}


	public Date getTransactionDate() {
		return transactionDate;
	}


	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}


	public Order getOrder() {
		return order;
	}


	public void setOrder(Order order) {
		this.order = order;
	}
	

}
