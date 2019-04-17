package com.routeplanner.shopping;

public class Order extends Basket {

	private PaymentInfo paymentInfo;
	
	
	public Order(User user, Basket basket) {
		super(user);
		manageBasket(basket);
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

	
	protected void manageBasket(Basket basket) {
		if (basket != null) {
			setTickets(basket.getTickets());
		}
	}
	
	
}
