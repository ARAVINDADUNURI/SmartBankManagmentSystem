package com.banking.dto;

import java.math.BigDecimal;

import com.banking.enums.LoanType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanApplicationRequestDTO {
	
	private Long accountId;
	
	private LoanType loanType;
	
	private BigDecimal requestedamount;
	
	private Integer tenureMonths;
	
	private String purpose;
	

}
