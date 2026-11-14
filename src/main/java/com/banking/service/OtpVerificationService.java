package com.banking.service;


import com.banking.dto.OtpVerificationRequestDTO;
import com.banking.dto.OtpVerificationResponseDTO;

public interface OtpVerificationService {
	
	public OtpVerificationResponseDTO generateOtp(OtpVerificationRequestDTO verificationRequestDTO);
	
	public OtpVerificationResponseDTO sendOtp(Long otpId);
	
	public OtpVerificationResponseDTO verifyOtp(Long otpId);
	
	public OtpVerificationRequestDTO resendOtp();

}
