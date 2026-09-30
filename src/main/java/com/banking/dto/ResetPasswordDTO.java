package com.banking.dto;

import org.springframework.web.client.RestClient;

public class ResetPasswordDTO {
	
	private String email;
	
	private String newPassword;
	
	private String confirmPassword;
	
	public ResetPasswordDTO() {
		
	}
	public ResetPasswordDTO(String email, String newPassword, String confirmPassword) {
		super();
		this.email = email;
		this.newPassword = newPassword;
		this.confirmPassword = confirmPassword;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getnewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword =  newPassword;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}
