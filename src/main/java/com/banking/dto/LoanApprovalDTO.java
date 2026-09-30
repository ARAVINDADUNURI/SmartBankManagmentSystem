package com.banking.dto;

import java.math.BigDecimal;

import com.banking.enums.LoanStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanApprovalDTO {
	
	private Long loanId;
	
	private LoanStatus loanStatus;
	
	private BigDecimal approvedAmount;
	
	private String adminMarks;

}
