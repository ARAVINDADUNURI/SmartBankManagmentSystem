package com.banking.service;

import java.math.BigDecimal;
import java.util.List;

import com.banking.dto.SavingsRequestDTO;
import com.banking.dto.SavingsResponseDTO;

public interface SavingsService {
	
	public SavingsResponseDTO createSavingsAccount(SavingsRequestDTO savingsRequestDTO);
	
	public SavingsResponseDTO getSavingsByAccountId(Long accountId);
	
	public List<SavingsResponseDTO> getAllSavingsAccount();
	
	public SavingsResponseDTO getSavingsAccountByCustomerId(Long customerId);
	
	public SavingsResponseDTO depositAmount(Long accountId, BigDecimal amount);
	
	public SavingsResponseDTO withDrawAmount(Long accountId, BigDecimal amount);
	
	

}
