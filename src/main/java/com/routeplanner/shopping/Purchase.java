package com.routeplanner.shopping;
import java.util.*;


public class Purchase extends DataModel
{
	private Date transactionDate;
	private int numberZones;
	private double totalPayment;
	private OLD_2009_User user;
	
	// TODO VERY OLD CODE (2009) - needs complete overhaul
	
	public Purchase(Date transactionDate,double totalPayment,OLD_2009_User user) 
	{
		super();
		this.transactionDate = transactionDate;
		this.totalPayment = totalPayment;
		this.user = user;
	}


	public Purchase(int purchase_id, Date transactionDate,double totalPayment,OLD_2009_User user)
	{
		super();
		setId(purchase_id);
		this.transactionDate = transactionDate;
		this.totalPayment = totalPayment;
		this.user = user;
	}
	


	public Date getTransactionDate() {
		return transactionDate;
	}



	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}



	public double getTotalPayment() {
		return totalPayment;
	}



	public void setTotalPayment(double totalPayment) {
		this.totalPayment = totalPayment;
	}


	public OLD_2009_User getUser() {
		return user;
	}


	public void setUser(OLD_2009_User user) {
		this.user = user;
	}
	
	
	
	
}
