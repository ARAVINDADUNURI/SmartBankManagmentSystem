package com.banking.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.banking.enums.AccountStatus;
import com.banking.enums.AccountType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponseDTO {
	
	private Long acountId;
	
	private String accountNumber;
	
	private AccountType accountType;
	
	private BigDecimal balance;
	
	private String  upiId;
	
	private String branchName;
	
	private String ifscCode;
	
	private  AccountStatus accountStatus;
	
	private  LocalDateTime createdAt;
	
	
	

}
