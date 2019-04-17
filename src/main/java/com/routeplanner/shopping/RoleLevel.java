package com.routeplanner.shopping;

// TODO UNFORTUNATELY THIS IS BOUND WITH THE DATABASE................
public enum RoleLevel {
	
	USER(1),
	MEMBER(2),
	ADMIN(3);
		
	public final int id;
	
	private RoleLevel(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	public static boolean isUser(User user) {
		return user.getRoles().stream().filter(r->r.getId() == USER.getId()).findFirst().isPresent();
	}
		
	public static boolean isMember(User user) {
		return user.getRoles().stream().filter(r->r.getId() == MEMBER.getId()).findFirst().isPresent();
	}
	
	public static boolean isAdmin(User user) {
		return user.getRoles().stream().filter(r->r.getId() == ADMIN.getId()).findFirst().isPresent();
	}
	
	
	
}
