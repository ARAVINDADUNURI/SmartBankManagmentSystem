package com.banking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.dto.AccountRequestDTO;
import com.banking.dto.AccountResponseDTO;
import com.banking.dto.UpdateAccountBalanceDTO;
import com.banking.dto.UpdateAccountRequestDTO;
import com.banking.service.AccountService;
import com.banking.service.CustomerService;

import io.micrometer.core.ipc.http.HttpSender.Response;

@RestController
@RequestMapping("/Account")
public class AccountDetailsController {
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	CustomerService customerService;
	
	@PostMapping("/create")
	public ResponseEntity addAccount(@RequestBody AccountRequestDTO accountRequestDTO ) {
		AccountResponseDTO accountResponseDTO = accountService.createAccount(accountRequestDTO);
		return new ResponseEntity(accountResponseDTO, HttpStatus.CREATED);
	}
	
	@GetMapping("/byId/{accountId}")
	public ResponseEntity getAccountById(@PathVariable Long accountId) {
		AccountResponseDTO accountById = accountService.getAccountById(accountId);
		return ResponseEntity.ok(accountById);
	}
	
	@GetMapping("/byAccNo/{accountNumber}")
	public ResponseEntity GetAccountByAccountNumber(@PathVariable String accountNumber) {
		AccountResponseDTO accountByAccountNumber = accountService.getAccountByAccountNumber(accountNumber);
		return ResponseEntity.ok(accountByAccountNumber);
	}
	
	@GetMapping("/byUpi/{upiId}")
	public ResponseEntity getAccountByUpiId(@PathVariable String upiId) {
		AccountResponseDTO accountByUpiId = accountService.getAccountByUpiId(upiId);
		return ResponseEntity.ok(accountByUpiId);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity getAllAccounts() {
		List<AccountResponseDTO> accountResponseDTO = accountService.getAllAccounts();
		return ResponseEntity.ok(accountResponseDTO);
	}
	
	@GetMapping("/byAccId/{accountId}")
	public ResponseEntity getAccountBalanceById(@PathVariable Long accountId) {
		AccountResponseDTO accountbalanceById = accountService.getAccountBalance(accountId);
		return ResponseEntity.ok(accountbalanceById);
	}
	
	@GetMapping("/accByCustomerId/{customerId}")
	public ResponseEntity getAccountsByCustomerId(@PathVariable Long customerId) {
		List<AccountResponseDTO> accountsByCustomerId = accountService.getAccountsByCustomerId(customerId);
		return ResponseEntity.ok(accountsByCustomerId);
	}
	
	@PutMapping("/updateById/{accountId}")
	public AccountResponseDTO updateAccountById(@PathVariable Long accountId,@RequestBody UpdateAccountRequestDTO accountRequestDTO) {
		return accountService.updateAccount(accountId, accountRequestDTO);
	}
	
	@PutMapping("/updateBalanceById/{accountId}")
	public AccountResponseDTO updateAccountBalanceById(@PathVariable Long accountId, @RequestBody UpdateAccountBalanceDTO accountBalanceDTO) {
		return accountService.updateAccountBalance(accountId, accountBalanceDTO);
	}
	
	@PatchMapping("/block/{accountId}")
	public ResponseEntity<AccountResponseDTO> blockAccount(@PathVariable Long accountId){
		AccountResponseDTO responseDTO = accountService.blockAccount(accountId);
		return ResponseEntity.ok(responseDTO);
	}
	
	@PatchMapping("/close/{accountId}")
	public ResponseEntity<AccountResponseDTO> closeAccount(@PathVariable Long accountId){
		AccountResponseDTO  responseDTO = accountService.closeAccount(accountId);
		return ResponseEntity.ok(responseDTO);
	}
	
	
}
