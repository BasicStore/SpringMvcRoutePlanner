package com.routeplanner.shopping;

import java.util.Locale;
import java.util.ResourceBundle;

public enum PassengerType {
	
	BOG_STANDARD(1, "rp.passenger.type.bog-standard", "rp.passenger.type.code.bog-standard", "", ""),
	STANDARD_PLUS(2, "rp.passenger.type.standard-plus", "rp.passenger.type.code.standard-plus", "", ""),
	OVER_75(3, "rp.passenger.type.over75", "rp.passenger.type.code.over75", "", ""),
	STUDENT_POOR(4, "rp.passenger.type.student-poor", "rp.passenger.type.code.student-poor", "", "");

	// TODO this must be dynamic
	final ResourceBundle prop = ResourceBundle.getBundle("messages", Locale.FRANCE);
	
	private final int id;

	private final String code;
	
	private final String type;
	
	private final String codeDisplay;
	
	private final String typeDisplay;
	
	
	private PassengerType(int id, String code, String type, String codeDisplay, String typeDisplay) {
		this.id = id;
		this.code = code;
		this.type= type;
		this.codeDisplay = prop.getString(code);
		this.typeDisplay = prop.getString(type);
	}



	public int getId() {
		return id;
	}


	public String getCode() {
		return code;
	}


	public String getType() {
		return type;
	}


	public String getCodeDisplay() {
		return codeDisplay;
	}


	public String getTypeDisplay() {
		return typeDisplay;
	}

	
}



