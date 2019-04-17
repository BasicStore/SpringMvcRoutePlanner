package com.routeplanner.shopping;

public abstract class AbstractShopping {

	private User user;
		
	public AbstractShopping() {
		
	}

	
	public AbstractShopping(User user) {
		this.user = user;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}
	
	
}
