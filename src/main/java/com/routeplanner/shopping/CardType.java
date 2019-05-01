package com.routeplanner.shopping;

public enum CardType {
	
	MASTERCARD(1, "rp.cardtype.mastercard"),
	VISA_DEBIT(2, "rp.cardtype.visa.debit"),
	VISA_CREDIT(3, "rp.cardtype.visa.credit");

	private final int id;

	private final String code;
	
	private CardType(int id, String code) {
		this.id = id;
		this.code = code;
	}

	public int getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	
	public static CardType fromId(int id) {
        for (CardType ct : CardType.values()){
            if (ct.getId() == id){
                return ct;
            }
        }

        throw new UnsupportedOperationException(
                "The id " + id + " is not supported!");
    }
	
}




