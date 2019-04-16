package com.routeplanner.shopping;

public enum RoleLevel {
	
	USER(1, "user-role"),
	MEMBER(2, "member-role"),
	ADMIN(3, "admin-role");
		
	private final int id;
	private final String lit;
	
	private RoleLevel(int id, String lit) {
		this.id = id;
		this.lit = lit;
	}

	public int getId() {
		return id;
	}

	public String getLit() {
		return lit;
	}
	

}
