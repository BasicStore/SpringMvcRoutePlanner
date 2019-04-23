package com.routeplanner.shopping;

import java.util.Locale;
import java.util.ResourceBundle;

public enum TicketType {
	
	PEAK(1, "rp.ticket.type.peak", ""),
	OFF_PEAK(2, "rp.ticket.type.off.peak", "");
	
	// TODO this must be dynamic
	final ResourceBundle prop = ResourceBundle.getBundle("messages", Locale.FRANCE);
	
	public final int id;
	
	public final String description;
	
	public final String descriptionTrans;
	
	private TicketType(int id, String description, String descriptionTrans) {
		this.id = id;
		this.description = description;
		this.descriptionTrans = prop.getString(description);
	}

	public int getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public String getDescriptionTrans() {
		return descriptionTrans;
	}
	
}
