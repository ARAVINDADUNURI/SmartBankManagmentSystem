package com.banking.serviceImpl;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.banking.dao.OtpVerification;
import com.banking.dto.OtpVerificationRequestDTO;
import com.banking.dto.OtpVerificationResponseDTO;
import com.banking.service.OtpVerificationService;

@Service
public class OtpVerificationServiceImpl implements OtpVerificationService {
	
	

	

	@Override
	public OtpVerificationResponseDTO sendOtp(Long otpId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OtpVerificationResponseDTO verifyOtp(Long otpId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OtpVerificationRequestDTO resendOtp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OtpVerificationResponseDTO generateOtp(OtpVerificationRequestDTO verificationRequestDTO) {
		// TODO Auto-generated method stub
		return null;
	}

}
