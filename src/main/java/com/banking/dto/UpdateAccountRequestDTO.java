package com.banking.dto;

import com.banking.enums.AccountType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAccountRequestDTO {
	
	private String branchName;
	
	private String ifscCode;
	
	private AccountType  accountType;

}
