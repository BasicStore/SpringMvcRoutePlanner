package com.routeplanner.shopping;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name="passenger_type")
public class PassengerType extends DataModel 
{
	private String type;
	
	private String code;
	
	public PassengerType() {
		
	}
		
	public PassengerType(String type, String code) 
	{
		this.type = type;
		this.code = code;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getCode() 
	{
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "PassengerType [type=" + type + ", code=" + code + ", getType()=" + getType() + ", getCode()="
				+ getCode() + ", getId()=" + getId() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	
	
}
