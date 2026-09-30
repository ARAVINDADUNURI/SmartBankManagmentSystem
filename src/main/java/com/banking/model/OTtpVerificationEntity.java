package com.banking.model;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "otp Verification")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OTtpVerificationEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name =  "otp_id")
	private Long otpId;
		
	@Column(name = "email", unique = true)
	private String	email;
	
	@Column(name = "otp_purpose")
	private String otp;
	
	@Column(name = "purpose")
	private String purpose;
	
	@Column(name = "expiry_time")
	private LocalDateTime expiryTime;
	
	@Column(name = "verified")
	private Boolean verfied;
	
	@Column(name = "created_At")
	private LocalDateTime createdAt;
	
//	Mappings
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Customer customer;

	public OTtpVerificationEntity(Customer customer, String email, String otp, String purpose, LocalDateTime expiryTime,
			Boolean verfied, LocalDateTime createdAt) {
		super();
		this.customer = customer;
		this.email = email;
		this.otp = otp;
		this.purpose = purpose;
		this.expiryTime = expiryTime;
		this.verfied = verfied;
		this.createdAt = createdAt;
	}
	
	
	

}
