package com.banking.controller;

import java.math.BigDecimal;
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

import com.banking.dto.SavingsRequestDTO;
import com.banking.dto.SavingsResponseDTO;
import com.banking.service.SavingsService;

@RestController
@RequestMapping("/savings")
public class SavingsController {
	
	@Autowired
	SavingsService savingsService;
	
	@PostMapping("/create")
	public ResponseEntity<SavingsResponseDTO> createSavingsAccount(@RequestBody SavingsRequestDTO savingsRequestDTO){
		SavingsResponseDTO savingsAccount = savingsService.createSavingsAccount(savingsRequestDTO);
		return new ResponseEntity(savingsAccount, HttpStatus.CREATED);
	}
	
	@GetMapping("/byAccountId/{accountId}")
	public ResponseEntity<SavingsResponseDTO> getSavingsByAccountId(@PathVariable Long accountId){
		SavingsResponseDTO savingsByAccountId = savingsService.getSavingsByAccountId(accountId);
		return ResponseEntity.ok(savingsByAccountId);
		
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<SavingsResponseDTO>>getAllSavingsAccounts(){
		List<SavingsResponseDTO> allSavingsAccount = savingsService.getAllSavingsAccount();
		return ResponseEntity.ok(allSavingsAccount);
		
	}
	
	@GetMapping("/byCustomerId/{customerId}")
	public ResponseEntity<SavingsResponseDTO> getSavingsAccountByCustomerId(@PathVariable Long customerId){
		SavingsResponseDTO savingsAccountByCustomerId = savingsService.getSavingsAccountByCustomerId(customerId);
		return ResponseEntity.ok(savingsAccountByCustomerId);
	}
	
	@PostMapping("/deposit/{accountId}/{amount}")
	public ResponseEntity<SavingsResponseDTO> depositAmount(@PathVariable Long accountId,@PathVariable BigDecimal amount){
		SavingsResponseDTO depositAmount = savingsService.depositAmount(accountId, amount);
		return ResponseEntity.ok(depositAmount);
	}
	
	@PostMapping("/withdraw/{accountId}/{amount}")
	public ResponseEntity<SavingsResponseDTO> withdrawAmount(@PathVariable Long accountId, @PathVariable BigDecimal amount){
		SavingsResponseDTO withDrawAmount = savingsService.withDrawAmount(accountId, amount);
		return ResponseEntity.ok(withDrawAmount);
	}

}
