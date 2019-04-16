package com.routeplanner.shopping;

public class Order extends Basket {

	private PaymentInfo paymentInfo;
	
	
	public Order() {
		
	}


	public Order(PaymentInfo paymentInfo) {
		this.paymentInfo = paymentInfo;
	}


	public PaymentInfo getPaymentInfo() {
		return paymentInfo;
	}


	public void setPaymentInfo(PaymentInfo paymentInfo) {
		this.paymentInfo = paymentInfo;
	}

}
