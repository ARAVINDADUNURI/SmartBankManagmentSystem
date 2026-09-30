package com.banking.dto;

import com.banking.enums.AccountType;
import com.banking.model.Customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequestDTO {
	
	private Customer customerId;
	
	private AccountType accountType;
	
	private String branch;
	
	private String ifscCode;

}
