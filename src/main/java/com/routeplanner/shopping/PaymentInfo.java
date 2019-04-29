package com.routeplanner.shopping;
import java.util.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import com.routeplanner.dm.DataModel;

@Entity
@Table(name="payment_info")
public class PaymentInfo extends DataModel 
{
	private static String CARD_NUMBER_PREFIX = "-XXXX-XXXX-XXXX";
	
	@Column(name="card_name")
	private String nameOnCard;
	
	@Column(name="card_type")
	private CardType cardType; 
	
	@Column(name="card_num")
	private String cardNumber;
	
	@Column(name="card_sec_code")
	private String securityCode;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date expiry_date;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date valid_from;
	
	@OneToOne
	private ContactDetails contactDetails;
	
	
	public PaymentInfo()
	{
		
	}
	
	public PaymentInfo(CardType cardType, String cardNumber, String securityCode,
		                      Date expiry_date, Date valid_from, String nameOnCard) 
	{
		this.nameOnCard = nameOnCard;
		this.cardType = cardType;
		this.cardNumber = cardNumber;
		this.securityCode = securityCode;
		this.expiry_date = expiry_date;
		this.valid_from = valid_from;
	}

	public CardType getCardType() {
		return cardType;
	}

	public void setCardType(CardType cardType) {
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
	
	
	public ContactDetails getContactDetails() {
		return contactDetails;
	}

	public void setContactDetails(ContactDetails contactDetails) {
		this.contactDetails = contactDetails;
	}
	
	/**
	 * Refreshes the payment card details, apart from the credit card number.
	 * The credit card number is represented as XXXX characters, but just displaying 
	 * the final 4 characters  
	 */
	public void setCardPresentationPostPurchase() {
		nameOnCard = StringUtils.isNotBlank(nameOnCard) && nameOnCard.length() > 4 
				? nameOnCard.substring(0, 3) + CARD_NUMBER_PREFIX : null; 
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
