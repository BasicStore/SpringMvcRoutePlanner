package com.routeplanner.shopping;

/**
 * Object to carry change of password credentials 
 */
public class UpdateUser {

	private String oldUsername;
	
	private String oldPassword;
	
	private String newPasword;
	
	private String cfmNewPasword;
	
	
	public UpdateUser() {
		
		
		
	}


	public String getOldUsername() {
		return oldUsername;
	}


	public void setOldUsername(String oldUsername) {
		this.oldUsername = oldUsername;
	}


	public String getOldPassword() {
		return oldPassword;
	}


	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}


	public String getNewPasword() {
		return newPasword;
	}


	public void setNewPasword(String newPasword) {
		this.newPasword = newPasword;
	}


	public String getCfmNewPasword() {
		return cfmNewPasword;
	}


	public void setCfmNewPasword(String cfmNewPasword) {
		this.cfmNewPasword = cfmNewPasword;
	}

	
}


