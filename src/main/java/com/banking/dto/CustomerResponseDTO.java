package com.banking.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.context.annotation.Role;

import com.banking.enums.CustomerStatus;
import com.banking.enums.Gender;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponseDTO {
	
	
	private Long customerId;
	
	private String firstName;
	
	private String lastName; 
	
	private String email;
	
	private String mobileNumber;
	
	private LocalDate dateOfBirth; 
	
	private Gender gender;   
	
	private String address;

	private String city;
	
	private String state;   
	
	private String pincode;
	
	private String panNumber;  
	
	private Role role;   
	
	private CustomerStatus status;
	
	private LocalDateTime createdAt;
	
	
}
