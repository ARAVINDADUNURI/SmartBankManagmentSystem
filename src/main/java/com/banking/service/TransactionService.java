package com.banking.service;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.engine.spi.Status;

import com.banking.dto.TransactionRequestDTO;
import com.banking.dto.TransactionResponseDTO;
import com.banking.enums.TransactionStatus;

public interface TransactionService {
	
	public TransactionResponseDTO createTransaction(TransactionRequestDTO transactionRequestDTO);
	
	public TransactionResponseDTO getTransactionById(Long transactionId);
	
	public List<TransactionResponseDTO> getAllTransactions();
	
	public List<TransactionResponseDTO> getTranctionsByAccountId(Long accountId);
	
	public List<TransactionResponseDTO> getTransactionsByCustomerId(Long customerId);
	
	public List<TransactionResponseDTO> getTransactionsByTransactionStatus(TransactionStatus transactionStatus);
	
	public List<TransactionResponseDTO> getTransactionsByDateRange(LocalDateTime startDate, LocalDateTime endDate);
	
	

}
