package com.routeplanner.shopping;

import java.util.Date;

public class Purchase {
	
	private User user;
	
	private Date transactionDate;  // TODO what is the best Date / LocalDateTime??????
	
	private Order order;
	
	
	public Purchase(User user, Date transactionDate, Order order) {
		this.user = user;
		this.transactionDate = transactionDate;
		this.order = order;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}


	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	
}
