package com.routeplanner.shopping;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="order")
public class Order extends DataModel {
	
	@OneToOne
	private User user;
	
	@OneToOne
	private PaymentInfo paymentInfo;
	
	@OneToOne
	private Basket basket;
	
	
	public Order() {
		
	}
	
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
