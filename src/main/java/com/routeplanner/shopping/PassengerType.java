package com.routeplanner.shopping;

import java.util.Locale;
import java.util.ResourceBundle;

public enum PassengerType {
	
	BOG_STANDARD(1, "rp.passenger.type.bog-standard", "rp.passenger.type.code.bog-standard"),
	STANDARD_PLUS(2, "rp.passenger.type.standard-plus", "rp.passenger.type.code.standard-plus"),
	OVER_75(3, "rp.passenger.type.over75", "rp.passenger.type.code.over75"),
	STUDENT_POOR(4, "rp.passenger.type.student-poor", "rp.passenger.type.code.student-poor");
	
	private final int id;

	private final String code;
	
	private final String type;
	
	private PassengerType(int id, String code, String type) {
		this.id = id;
		this.code = code;
		this.type= type;
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

	
	public static PassengerType fromId(int id) {
        for (PassengerType pt : PassengerType.values()){
            if (pt.getId() == id){
                return pt;
            }
        }

        throw new UnsupportedOperationException(
                "The id " + id + " is not supported!");
    }
	
}



