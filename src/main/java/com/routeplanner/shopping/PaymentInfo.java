package com.routeplanner.shopping;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import com.routeplanner.shopping.converters.CardTypeConverter;

@Entity
@Table(name="payment_info")
public class PaymentInfo extends DataModel 
{
	private static String CARD_NUMBER_PREFIX = "-XXXX-XXXX-XXXX";
	
	@NotNull
	@Column(name="card_name", length = 50)
	private String nameOnCard;
	
	@NotNull
	@Column(name="card_type")
	@Convert(converter = CardTypeConverter.class)
	private CardType cardType; 
	
	@NotNull
	@Size(min=16,max=16)
	@Column(name="card_num")
	private String cardNumber;
	
	@NotNull
	@Column(name="card_sec_code")
	@Size(min=3,max=3)
	private String securityCode;
		
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate expiryDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate validFrom;
	
	
	public PaymentInfo()
	{
		
	}
	
	public PaymentInfo(CardType cardType, String cardNumber, String securityCode,
			LocalDate expiryDate, LocalDate validFrom, String nameOnCard) 
	{
		this.nameOnCard = nameOnCard;
		this.cardType = cardType;
		this.cardNumber = cardNumber;
		this.securityCode = securityCode;
		this.expiryDate = expiryDate;
		this.validFrom = validFrom;
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

	public LocalDate getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}

	public LocalDate getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(LocalDate validFrom) {
		this.validFrom = validFrom;
	}

	public String getNameOnCard() {
		return nameOnCard;
	}


	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
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
				+ ", securityCode=" + securityCode + ", expiry_date=" + expiryDate + ", valid_from=" + validFrom
				+ ", getCardType()=" + getCardType() + ", getCardNumber()=" + getCardNumber() + ", getSecurityCode()="
				+ getSecurityCode() + ", getExpiry_date()=" + getExpiryDate() + ", getValid_from()=" + getValidFrom()
				+ ", getNameOnCard()=" + getNameOnCard() + ", getId()=" + getId() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	
}
