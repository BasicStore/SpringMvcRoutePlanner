package com.routeplanner.shopping;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="contact_details")
public class ContactDetails extends DataModel 
{
	private String title;
	
	private String firstname;
	
	private String initials;
	
	private String lastname;
	
	@Column(name="address_line_1")
	private String addressLine1;
	
	@Column(name="address_line_2")
	private String addressLine2;
	
	@Column(name="address_line_3")
	private String addressLine3;
	
	private String city;
	
	@Column(name="region_or_state")
	private String regionOrState;
	
	private String country;
	
	private String email;
	
	@Column(name="mobile_phone")
	private String mobileTel;
	
	@Column(name="home_phone")
	private String homeTel;
	
	@Column(name="office_phone")
	private String officeTel;
	
	
	public ContactDetails() {
		
	}


	public ContactDetails(String title, String firstname, String initials, String lastname, String addressLine1,
			String addressLine2, String addressLine3, String city, String regionOrState, String country, String email,
			String mobileTel, String homeTel, String officeTel) {
		this.title = title;
		this.firstname = firstname;
		this.initials = initials;
		this.lastname = lastname;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.addressLine3 = addressLine3;
		this.city = city;
		this.regionOrState = regionOrState;
		this.country = country;
		this.email = email;
		this.mobileTel = mobileTel;
		this.homeTel = homeTel;
		this.officeTel = officeTel;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public String getInitials() {
		return initials;
	}


	public void setInitials(String initials) {
		this.initials = initials;
	}


	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public String getAddressLine1() {
		return addressLine1;
	}


	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}


	public String getAddressLine2() {
		return addressLine2;
	}


	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}


	public String getAddressLine3() {
		return addressLine3;
	}


	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getRegionOrState() {
		return regionOrState;
	}


	public void setRegionOrState(String regionOrState) {
		this.regionOrState = regionOrState;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getMobileTel() {
		return mobileTel;
	}


	public void setMobileTel(String mobileTel) {
		this.mobileTel = mobileTel;
	}


	public String getHomeTel() {
		return homeTel;
	}


	public void setHomeTel(String homeTel) {
		this.homeTel = homeTel;
	}


	public String getOfficeTel() {
		return officeTel;
	}


	public void setOfficeTel(String officeTel) {
		this.officeTel = officeTel;
	}
	
}
