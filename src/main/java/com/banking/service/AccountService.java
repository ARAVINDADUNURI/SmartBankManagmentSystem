package com.banking.service;

import java.util.List;

import com.banking.dto.AccountRequestDTO;
import com.banking.dto.AccountResponseDTO;
import com.banking.dto.UpdateAccountBalanceDTO;
import com.banking.dto.UpdateAccountRequestDTO;

public interface AccountService {
	
	public AccountResponseDTO createAccount(AccountRequestDTO accountRequestDTO);
	
	public AccountResponseDTO getAccountById(Long accountId);
	
	public AccountResponseDTO getAccountByAccountNumber(String accountNumber);
	
	public AccountResponseDTO getAccountByUpiId(String upiId);
	
	public List<AccountResponseDTO> getAccountsByCustomerId(Long customerId);
	
	public List<AccountResponseDTO> getAllAccounts();
	
	public AccountResponseDTO updateAccount(Long accountId, UpdateAccountRequestDTO accountRequestDTO);
	
	public AccountResponseDTO blockAccount(Long accountId);
	
	public AccountResponseDTO closeAccount(Long accountId);
	
	public AccountResponseDTO getAccountBalance(Long accountId);
	
	public AccountResponseDTO updateAccountBalance(Long accountId, UpdateAccountBalanceDTO accountBalanceDTO);
	
	

}
