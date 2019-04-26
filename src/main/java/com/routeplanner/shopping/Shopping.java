package com.routeplanner.shopping;

public class Shopping {

	private User user;
	private Basket basket;
	private Order order;
	private Purchase purchase;
		
	public Shopping(User user) {
		this.user = user;
		this.basket = new Basket();
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

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Purchase getPurchase() {
		return purchase;
	}

	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}
	
	public void postOrderPruneBasket() {
		this.basket = null;
	}
	
	
	public void postPurchasePruneOrder() {
		postOrderPruneBasket();
		this.purchase.getOrder().getPaymentInfo().setCardPresentationPostPurchase();
	}
	
	
	public void startFreshJourneyPrunePreviousPurchase() {
		postOrderPruneBasket();
		this.order = null;
		this.purchase = null;
	}
	
	
	@Override
	public String toString() {
		return "Shopping [user=" + user + ", basket=" + basket + ", order=" + order + ", purchase=" + purchase
				+ ", getUser()=" + getUser() + ", getBasket()=" + getBasket() + ", getOrder()=" + getOrder()
				+ ", getPurchase()=" + getPurchase() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	
}
