package com.routeplanner.shopping;

public enum TicketType {

	PEAK(1, "peak"),
	OFF_PEAK(2, "off-peak");
	
	public final int id;
	
	public final String description;
	
	private TicketType(int id, String description) {
		this.id = id;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}
	
}
