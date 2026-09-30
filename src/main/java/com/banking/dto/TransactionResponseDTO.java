package com.banking.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.resource.transaction.spi.TransactionStatus;

import com.banking.enums.FraudStatus;
import com.banking.enums.TransactionType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponseDTO {
	
	private Long transactionId;
	
	private String transactionReference;
	
	private String senderAccountNumber;
	
	private String reciverAccountNumber;
	
	private BigDecimal amount;	
	
	private TransactionType transactionType;
	
	private TransactionStatus transactionStatus;
	
	private String description;
	
	private LocalDateTime transactionDate;
	
	private FraudStatus fraudStatus;

}
