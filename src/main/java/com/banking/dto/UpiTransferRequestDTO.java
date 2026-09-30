package com.banking.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpiTransferRequestDTO {
	
	private String senderAccountNumber;
	
	private String reciverAccountNumber;
	
	private BigDecimal amount;
	
	private String description;

}
