package com.banking.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.resource.transaction.spi.TransactionStatus;

import com.banking.enums.TransactionType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MiniStatmentDTO {
	
	private String transactionReference;
	
	private TransactionType transactionType;
	
	private BigDecimal amount;
	
	private String description;
	
	private TransactionStatus transactionStatus;
	
	private LocalDateTime transactionDate;

}
