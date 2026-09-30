package com.banking.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.banking.enums.LoanStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanResponseDTO {
	
	private Long loanId;
	
	private Long customerId;
	
	private Long accountId;
	
	private BigDecimal requestedAmount;
	
	private BigDecimal approvedAmount;
	
	private Integer tenureMonths;
	
	private BigDecimal intrestRate;
	
	private String purpose;
	
	private LoanStatus loanStatus;
	
	private LocalDateTime appliedDate;
	
	private LocalDateTime reviewedDate;
	
	private String adminMarks;
	

}
