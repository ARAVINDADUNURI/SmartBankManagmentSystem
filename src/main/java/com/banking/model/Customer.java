package com.banking.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.management.relation.Role;

import com.banking.dto.RegisterRequestDTO;
import com.banking.enums.CustomerStatus;
import com.banking.enums.Gender;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_id")
	private Long customerId;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "email", unique = true)
	private String email;
	
	@Column(name = "mobile_no", unique = true)
	private Long MobileNo;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "dob")
	private LocalDate dateOfBirth;
	
	@Column(name = "gender")
	private Gender gender;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "pincode", unique = true)
	private Integer pinCode;
	
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "state")
	private String state;
	
	@Column(name = "pan_number", unique = true)
	private String panNumber;
	
	@Column(name = "aadhar")
	private String aadharNumber;
	
	@Column(name = "role")
	private Role role;
	
	@Column(name = "customer_status")
	private CustomerStatus status;
	
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;
	
//	Mappings
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Account accounts;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Loan loans;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private OTtpVerificationEntity oTtpVerifications;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Savings savings;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private FraudAlert fraudAlerts;

	public Customer(String firstName, String lastName, String email, Long mobileNo, String password,
			LocalDate dateOfBirth, Gender gender, String address, String city, String state, String aadharNumber,
			Role role, CustomerStatus status, LocalDateTime createdAt, LocalDateTime updatedAt, Account accounts,
			Loan loans, OTtpVerificationEntity oTtpVerifications, Savings savings, FraudAlert fraudAlerts,String panNumber, Integer pinCode) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		MobileNo = mobileNo;
		this.password = password;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.address = address;
		this.city = city;
		this.state = state;
		this.aadharNumber = aadharNumber;
		this.role = role;
		this.status = status;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.accounts = accounts;
		this.loans = loans;
		this.oTtpVerifications = oTtpVerifications;
		this.savings = savings;
		this.fraudAlerts = fraudAlerts;
		this.panNumber = panNumber;
		this.pinCode = pinCode;
	}

		
	/*public Customer(String firstName, String lastName, String email, Long mobileNo, String password,
			LocalDate dateOfBirth, Gender gender, String address, String city, String state, String aadharNumber,
			Role role, CustomerStatus status, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		MobileNo = mobileNo;
		this.password = password;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.address = address;
		this.city = city;
		this.state = state;
		this.aadharNumber = aadharNumber;
		this.role = role;
		this.status = status;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}*/
	
	
	
}
