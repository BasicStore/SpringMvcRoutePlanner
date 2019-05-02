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

	@Column(name="email", length = 60)
	private String email;
	
	@Column(name="password", length = 30)
	private String password;
	
	@Column(name="name", length = 30)
	private String username;
	
	@Column(name="last_name", length = 30)
	private String lastName;
	
	@Column(name="active")
	private int active;
	
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable(name="user_role", joinColumns=@JoinColumn(name="user_id"))
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
		return RoleLevel.getHighestRole(this);
	}

	@Override
	public String toString() {
		return "User [email=" + email + ", password=" + password + ", username=" + username + ", lastName=" + lastName
				+ ", active=" + active + ", roles=" + roles + ", getEmail()=" + getEmail() + ", getPassword()="
				+ getPassword() + ", getLastName()=" + getLastName() + ", getUsername()=" + getUsername()
				+ ", getActive()=" + getActive() + ", getRoles()=" + getRoles() + ", getRoleLevel()=" + getRoleLevel()
				+ ", getId()=" + getId() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
	
}
