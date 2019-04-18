package com.routeplanner.shopping;
import java.util.ResourceBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


// TODO UNFORTUNATELY THIS IS BOUND WITH THE DATABASE................
public enum RoleLevel {
	
	USER(1, "role.level.user"),
	MEMBER(2, "role.level.member"),
	ADMIN(3, "role.level.admin");

	final static ResourceBundle prop = ResourceBundle.getBundle("application");
	
	public final int id;
	
	public final String roleName;
	
	private RoleLevel(int id, String roleName) {
		this.id = id;
		this.roleName = roleName;
	}
	
	public int getId() {
		return id;
	}
	
	public static boolean isMember(User user) {
		if (user.getRoles() != null) {
			return user.getRoles().stream().filter(r->r.getRole().equals(prop.getString("role.level.member"))).findFirst().isPresent();
		}
		
		return false;
	}
	
	public static boolean isAdmin(User user) {
		if (user.getRoles() != null) { 
			return user.getRoles().stream().filter(r->r.getRole().equals(prop.getString("role.level.admin"))).findFirst().isPresent();
		}
		return false;
	}

	
	// TODO THIS IS A TEMPORARY SOLUTION UNTIL SPRING SECURITY IS IMPLEMENTED	
	public static RoleLevel getHighestRole(User user) {
		if (isAdmin(user)) {
			return RoleLevel.ADMIN;
		} else if (isMember(user)) {
			return RoleLevel.MEMBER;
		} else {
			return RoleLevel.USER;
		}
	}

	public String getRoleName() {
		return roleName;
	}
	
}
