package com.banking.dto;

import java.io.ObjectInputFilter.Status;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SavingsResponseDTO {
	
	private Long goalId;
	
	private Long customerId;
	
	private String goalName;
	
	private BigDecimal targetedAmount;
	
	private BigDecimal currentAmount;
	
	private LocalDate targetDate;
	
	private Status goalStatus;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;

}
