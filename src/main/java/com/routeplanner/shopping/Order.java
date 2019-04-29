package com.routeplanner.shopping;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.routeplanner.dm.DataModel;

@Entity
@Table(name="orders")
public class Order extends DataModel {
	
	@OneToOne
	private User user;
	
	@OneToOne
	private PaymentInfo paymentInfo;
	
	@OneToOne
	private Basket basket;
	
	
	public Order() {
		super();
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Basket getBasket() {
		return basket;
	}

	public void setBasket(Basket basket) {
		this.basket = basket;
	}

	@Override
	public String toString() {
		return "Order [user=" + user + ", paymentInfo=" + paymentInfo + ", basket=" + basket + ", getPaymentInfo()="
				+ getPaymentInfo() + ", getUser()=" + getUser() + ", getBasket()=" + getBasket() + ", getId()="
				+ getId() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
}
