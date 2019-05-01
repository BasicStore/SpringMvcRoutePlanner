package com.routeplanner.shopping;

public enum TicketType {
	
	PEAK(1, "rp.ticket.type.peak"),
	OFF_PEAK(2, "rp.ticket.type.off.peak");
	
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
	
	
	public static TicketType fromId(int id) {
        for (TicketType tt : TicketType.values()){
            if (tt.getId() == id){
                return tt;
            }
        }

        throw new UnsupportedOperationException(
                "The id " + id + " is not supported!");
    }
	
	
}
