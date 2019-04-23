package com.routeplanner.shopping;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="purchase")
public class Purchase extends DataModel {
	
	@OneToOne
	private User user;
	
	@Column(name="transaction")
	private Date transactionDate;  
	
	@OneToOne
	private Order order;
	
	public Purchase() {
		
	}
		
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

	@Override
	public String toString() {
		return "Purchase [user=" + user + ", transactionDate=" + transactionDate + ", order=" + order
				+ ", getTransactionDate()=" + getTransactionDate() + ", getId()=" + getId() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
}
