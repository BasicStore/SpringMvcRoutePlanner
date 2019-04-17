package com.routeplanner.shopping;

public class Order {

	private User user;
	
	private PaymentInfo paymentInfo;
	
	private Basket basket;
	
	
	public Order(User user, Basket basket) {
		this.user = user;
		this.basket = basket;
	}

	
	public Order(PaymentInfo paymentInfo, User user, Basket basket) {
		this(user, basket);
		this.paymentInfo = paymentInfo;
	}


	public PaymentInfo getPaymentInfo() {
		return paymentInfo;
	}


	public void setPaymentInfo(PaymentInfo paymentInfo) {
		this.paymentInfo = paymentInfo;
	}
	
}
