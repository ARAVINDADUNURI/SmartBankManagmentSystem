package com.banking.dto;

import com.banking.enums.OtpPurpose;

public class OtpVerificationDTO {
	
	private String email;
	
	private String otp;
	
	private OtpPurpose purpose;
	
	public OtpVerificationDTO() {
		
	}
	
	public OtpVerificationDTO(String email, String otp, OtpPurpose purpose) {
		super();
		this.email = email;
		this.otp = otp;
		this.purpose = purpose;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public OtpPurpose getPurpose() {
		return purpose;
	}
	public void setPurpose(OtpPurpose purpose) {
		this.purpose = purpose;
	}

}
