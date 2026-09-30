package com.banking.dto;

import java.time.LocalDate;

import javax.management.relation.Role;

import com.banking.enums.Gender;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDTO {
	
	private String firstName;
	
	private String lastName; 
	
	private String email;
	
	private Long mobileNumber;
	
	private String password;
	
	private LocalDate dateOfBirth;
	
	private Gender	gender;
	
	private String address;
	
	private String city;
	
	private String state;
	
	private String pincode;
	
	private String panNumber;
	
	private String aadhaarNumber;

	public Role getRole;

}
