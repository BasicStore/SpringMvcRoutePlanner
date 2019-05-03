package com.routeplanner.shopping;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="registration_details")
public class RegistrationDetails extends ContactDetails {

	@NotNull
	@OneToOne
	private User user;
	
	public RegistrationDetails() {
		super();
		this.user = new User();
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
