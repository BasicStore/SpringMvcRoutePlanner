package com.routeplanner.shopping;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="role")
public class Role extends DataModel {

	private String role;
	
	public Role() {
		
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Role [role=" + role + ", getRole()=" + getRole() + ", getId()=" + getId() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
}
