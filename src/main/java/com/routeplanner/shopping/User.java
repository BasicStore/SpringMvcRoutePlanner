package com.routeplanner.shopping;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="user")
public class User extends DataModel {

//	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO)
//	@Column(name="user_id")
//	private int id;
	
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String password;
	
	@Column(name="name")
	private String username;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="active")
	private int active;
	
	private RoleLevel roleLevel;
		
	
	// ORIG working flat
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable(name="user_role", joinColumns=@JoinColumn(name="user_id"))
	
	
	
	// OTHER OPTION
//	@ManyToMany
//    @JoinTable( 
//        name = "users_roles", 
//        joinColumns = @JoinColumn(
//          name = "user_id", referencedColumnName = "id"), 
//        inverseJoinColumns = @JoinColumn(
//          name = "roles_role_id", referencedColumnName = "roleId")) 
	private Set<Role> roles;
	
	public User() {
		
	}

	public User(User user) {
		this.active = user.getActive();
		this.username = user.getUsername();
		this.lastName = user.getLastName();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.roles = user.getRoles();
	}

	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getActive() {
		return active;
	}


	public void setActive(int active) {
		this.active = active;
	}


	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public RoleLevel getRoleLevel() {
		return roleLevel;
	}

	public void setRoleLevel(RoleLevel roleLevel) {
		this.roleLevel = roleLevel;
	}

	
}
