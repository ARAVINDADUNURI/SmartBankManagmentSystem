package com.banking.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.model.OTtpVerificationEntity;

public interface OtpVerification extends JpaRepository<OTtpVerificationEntity, Long> {

}
