package com.banking.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.dto.TransactionRequestDTO;
import com.banking.dto.TransactionResponseDTO;
import com.banking.enums.TransactionStatus;
import com.banking.service.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionsController {
	
	@Autowired
	TransactionService transactionService;
	
	
	@PostMapping("/create")
	public ResponseEntity<TransactionResponseDTO> createTransaction(@RequestBody TransactionRequestDTO requestDTO){
		TransactionResponseDTO transaction = transactionService.createTransaction(requestDTO);
		return new ResponseEntity(transaction, HttpStatus.CREATED);
		
	}
	
	@GetMapping("/getById/{transactionId}")
	public ResponseEntity<TransactionResponseDTO> getTransactionById(@PathVariable Long transactionId){
		TransactionResponseDTO transactionById = transactionService.getTransactionById(transactionId);
		return ResponseEntity.ok(transactionById);
	}
	
	@GetMapping("/getAllTransactions")
	public ResponseEntity<List<TransactionResponseDTO>> getAllTransactions(){
		List<TransactionResponseDTO> allTransactions = transactionService.getAllTransactions();
		return ResponseEntity.ok(allTransactions);
	}
	
	@GetMapping("/byCustomerId/{customerId}")
	public ResponseEntity getTransactionsByCustomerId(@PathVariable Long customerId) {
		List<TransactionResponseDTO> transactionsByCustomerId = transactionService.getTransactionsByCustomerId(customerId);
		return ResponseEntity.ok(transactionsByCustomerId);
	}
	
	@GetMapping("/byAccountId/{accountId}")
	public ResponseEntity<List<TransactionResponseDTO>> getTransactionsByAccountId(@PathVariable Long accountId){
		List<TransactionResponseDTO> tranctionsByAccountId = transactionService.getTranctionsByAccountId(accountId);
		return ResponseEntity.ok(tranctionsByAccountId);
	}
	
	@GetMapping("/byTransactionStatus/{transactionStatus}")
	public ResponseEntity<List<TransactionResponseDTO>> getTransactionsByTransactionStatus(@PathVariable TransactionStatus transactionStatus){
		List<TransactionResponseDTO> transactionsByTransactionStatus = transactionService.getTransactionsByTransactionStatus(transactionStatus);
		return ResponseEntity.ok(transactionsByTransactionStatus);
	}
	
	@GetMapping("/byDateRanges/{startDate}/{endDate}")
	public ResponseEntity<List<TransactionResponseDTO>> getTransactionsByDateRange(@PathVariable LocalDateTime startDate, LocalDateTime endDate){
		List<TransactionResponseDTO> transactionsByDateRange = transactionService.getTransactionsByDateRange(startDate, endDate);
		return ResponseEntity.ok(transactionsByDateRange);
	}

}
