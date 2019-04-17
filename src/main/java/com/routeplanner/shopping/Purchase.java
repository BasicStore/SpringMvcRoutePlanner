package com.routeplanner.shopping;

import java.util.Date;

public class Purchase extends Order {

	// TODO what is the best Date / LocalDateTime??????
	private Date transactionDate;
	
	public Purchase(Date transactionDate, PaymentInfo paymentInfo, User user, Basket basket) {
		super(paymentInfo, user, basket);
		this.transactionDate = transactionDate;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}


	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	
}
