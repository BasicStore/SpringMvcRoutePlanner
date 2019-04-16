package com.routeplanner.shopping;

import java.util.List;

public class Person {

	private ContactDetails contactDetails;
	
	private List<PaymentInfo> paymentInfoList;
		
	public Person() {
	
		
	}

	public Person(ContactDetails contactDetails, List<PaymentInfo> paymentInfoList) {
		this.contactDetails = contactDetails;
		this.paymentInfoList = paymentInfoList;
	}

	public ContactDetails getContactDetails() {
		return contactDetails;
	}

	public void setContactDetails(ContactDetails contactDetails) {
		this.contactDetails = contactDetails;
	}

	public List<PaymentInfo> getPaymentInfoList() {
		return paymentInfoList;
	}

	public void setPaymentInfoList(List<PaymentInfo> paymentInfoList) {
		this.paymentInfoList = paymentInfoList;
	}
	
	
}
