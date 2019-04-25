package com.routeplanner.shopping;
import java.util.Locale;
import java.util.ResourceBundle;

public enum CardType {
	
	MASTERCARD(1, "rp.cardtype.mastercard", ""),
	VISA_DEBIT(2, "rp.cardtype.visa.debit", ""),
	VISA_CREDIT(3, "rp.cardtype.visa.credit", "");

	// TODO this must be dynamic
	final ResourceBundle prop = ResourceBundle.getBundle("messages", Locale.FRANCE);
	
	private final int id;

	private final String code;
	
	private final String codeDisplay;

	
	private CardType(int id, String code, String codeDisplay) {
		this.id = id;
		this.code = code;
		this.codeDisplay = prop.getString(code);
	}


	public int getId() {
		return id;
	}


	public String getCode() {
		return code;
	}

	public String getCodeDisplay() {
		return codeDisplay;
	}
	
}




