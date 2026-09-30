package com.banking.dto;

import java.time.LocalDate;

import com.banking.enums.Gender;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCustomerRequestDTO {
	
	private String firstName;
	
	private String lastName;
	
	private Long mobileNumber;
	
	private LocalDate dateOfBirth;
			
	private Gender gender;	
			
	private String address	;
			
	private String city;	
			
	private String state;	
			
	private Integer pincode;
			

}
