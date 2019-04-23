package com.routeplanner.shopping;
import java.util.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="payment_info")
public class PaymentInfo extends DataModel 
{
	@Column(name="card_name")
	private String nameOnCard;
	
	@Column(name="card_type")
	private String cardType; 
	
	@Column(name="card_num")
	private String cardNumber;
	
	@Column(name="card_sec_code")
	private String securityCode;
	
	
	private Date expiry_date;
	
	
	private Date valid_from;
	
	
	public PaymentInfo()
	{
		
	}
	
	public PaymentInfo(String cardType, String cardNumber, String securityCode,
		                      Date expiry_date, Date valid_from, String nameOnCard) 
	{
		this.nameOnCard = nameOnCard;
		this.cardType = cardType;
		this.cardNumber = cardNumber;
		this.securityCode = securityCode;
		this.expiry_date = expiry_date;
		this.valid_from = valid_from;
	}


	public String getCardType() {
		return cardType;
	}



	public void setCardType(String cardType) {
		this.cardType = cardType;
	}



	public String getCardNumber() {
		return cardNumber;
	}



	public void setCardNumber(String cardNumber) 
	{
		this.cardNumber = cardNumber;
	}


	public String getSecurityCode() {
		return securityCode;
	}



	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}



	public Date getExpiry_date() {
		return expiry_date;
	}



	public void setExpiry_date(Date expiry_date) {
		this.expiry_date = expiry_date;
	}

	
	public Date getValid_from() {
		return valid_from;
	}



	public void setValid_from(Date valid_from) {
		this.valid_from = valid_from;
	}
	

	public String getNameOnCard() {
		return nameOnCard;
	}


	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}
	
	
	// TODO refactor
	private String generateSafeCardNumber(String cardNumber)
	{
//		int minDigits = 10;
//		if (cardNumber.length() > minDigits)
//		{
//			int digitsStart = 11;
//			String prefix = "***";
//			String suffix = cardNumber.substring(11);
//			this.safeCardNumber = prefix + suffix;
//		}
//		else
//		{
//			this.safeCardNumber = "";
//		}
		
		return cardNumber;
	}

	@Override
	public String toString() {
		return "PaymentInfo [nameOnCard=" + nameOnCard + ", cardType=" + cardType + ", cardNumber=" + cardNumber
				+ ", securityCode=" + securityCode + ", expiry_date=" + expiry_date + ", valid_from=" + valid_from
				+ ", getCardType()=" + getCardType() + ", getCardNumber()=" + getCardNumber() + ", getSecurityCode()="
				+ getSecurityCode() + ", getExpiry_date()=" + getExpiry_date() + ", getValid_from()=" + getValid_from()
				+ ", getNameOnCard()=" + getNameOnCard() + ", getId()=" + getId() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	
}
