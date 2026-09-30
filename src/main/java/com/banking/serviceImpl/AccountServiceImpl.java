package com.banking.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.banking.dao.AccountRepository;
import com.banking.dao.CustomerRepository;
import com.banking.dto.AccountRequestDTO;
import com.banking.dto.AccountResponseDTO;
import com.banking.dto.UpdateAccountBalanceDTO;
import com.banking.dto.UpdateAccountRequestDTO;
import com.banking.model.Account;
import com.banking.model.Customer;
import com.banking.service.AccountService;

public class AccountServiceImpl implements AccountService {
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	CustomerRepository customerRepository;

	@Override
	public AccountResponseDTO createAccount(AccountRequestDTO accountRequestDTO) {
		Account account = new Account();
		account.setAccountType(accountRequestDTO.getAccountType());
		account.setBranchName(accountRequestDTO.getBranch());
		account.setIfscCode(accountRequestDTO.getIfscCode());
		Customer customer = customerRepository.findById(accountRequestDTO.getCustomerId())
		        .orElseThrow(() -> new CustomerNotFoundException(
		                "Customer not found with Id: " + accountRequestDTO.getCustomerId()));

		account.setCustomer(customer);
		
		return null;
	}

	@Override
	public AccountResponseDTO getAccountById(Long accountId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccountResponseDTO getAccountByAccountNumber(String accountNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccountResponseDTO getAccountByUpiId(String upiId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AccountResponseDTO> getAccountsByCustomerId(Long customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AccountResponseDTO> getAllAccounts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccountResponseDTO updateAccount(Long accountId, UpdateAccountRequestDTO accountRequestDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccountResponseDTO blockAccount(Long accountId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccountResponseDTO closeAccount(Long accountId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccountResponseDTO getAccountBalance(Long accountId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccountResponseDTO updateAccountBalance(Long accountId, UpdateAccountBalanceDTO accountBalanceDTO) {
		// TODO Auto-generated method stub
		return null;
	}

}
