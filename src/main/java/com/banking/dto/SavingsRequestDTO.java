package com.banking.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SavingsRequestDTO {
	
	private String goalName;
	
	private BigDecimal targatedAmount;
	
	private BigDecimal currentAmount;
	
	private LocalDate targetDate;

}
