package com.banking.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionReportDTO {
	
	private String reportPeriod;
	
	private Long totalTransactions;
	
	private Long sucessfulTransactions;
	
	private Long failedTransactions;
	
	private BigDecimal totalTransactionAmount;
	
	private BigDecimal totalTransferAmount;
	
	private Long flaggedTransactions;

}
