package com.banking.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAccountBalanceDTO {
	
	private Long accountId;
	
	private BigDecimal amount;

}
