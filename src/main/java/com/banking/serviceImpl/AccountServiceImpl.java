package com.banking.serviceimpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.dao.AccountRepository;
import com.banking.dao.CustomerRepository;
import com.banking.dto.AccountRequestDTO;
import com.banking.dto.AccountResponseDTO;
import com.banking.dto.UpdateAccountBalanceDTO;
import com.banking.dto.UpdateAccountRequestDTO;
import com.banking.enums.AccountStatus;
import com.banking.exception.AccountNotFoundException;
import com.banking.exception.AccountNumberNotFoundException;
import com.banking.exception.AccountStatusFoundException;
import com.banking.exception.CustomerNotFoundException;
import com.banking.exception.UPINotFoundException;
import com.banking.model.Account;
import com.banking.model.Customer;
import com.banking.service.AccountService;
@Service
public class AccountServiceImpl implements AccountService {
	
	private static final Logger log = LoggerFactory.getLogger(AccountService.class);
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	@Override
	public AccountResponseDTO createAccount(AccountRequestDTO accountRequestDTO) {
		
		log.trace("Entered Into add customer method in AccountService");
		log.debug("createAccount method in AccountService : {}", accountRequestDTO != null ? accountRequestDTO.getCustomerId() : null);
		
		Account account = new Account();
		
		log.trace("Started copying accountRequestDTO into Account Object");
		account.setAccountType(accountRequestDTO.getAccountType());
		account.setBranchName(accountRequestDTO.getBranch());
		account.setIfscCode(accountRequestDTO.getIfscCode());
		
		Customer customer = customerRepository.findById(accountRequestDTO.getCustomerId())
				.orElseThrow(() -> {
					log.error("Customer not found with Id: {}", accountRequestDTO.getCustomerId());
					return new CustomerNotFoundException("Customer not found with Id: " + accountRequestDTO.getCustomerId());
				});

		account.setCustomer(customer);
		
		Account savedAccount = accountRepository.save(account);
		log.info("Successfully created account with ID: {} for customer ID: {}", savedAccount.getAccountId(),customer.getCustomerId());
		
		AccountResponseDTO responseDTO = new AccountResponseDTO();
		BeanUtils.copyProperties(savedAccount, responseDTO); // Note: Fixed argument order (source, target)
		
		log.trace("Exiting createAccount method");
		return responseDTO;
	}


	@Override
	public AccountResponseDTO getAccountById(Long accountId) {
		log.trace("Entered into getAccoutById Method");
		log.debug("Fetching Account Details found for AccountId :{}");

		Account account = accountRepository.findById(accountId)
				.orElseThrow(() -> {
					log.warn("Account not found with AccountId : {}", accountId);
					return new AccountNotFoundException("No Accounts Have Been found with provided account Id : " + accountId);
				});
		
		log.info("Sucessfully Retrive account with accountId ");
		AccountResponseDTO responseDTO = new AccountResponseDTO();
		BeanUtils.copyProperties(account, responseDTO);
		
		log.trace("Exiting getAccountById Method");
		return responseDTO;
	}

	@Override
	public AccountResponseDTO getAccountByAccountNumber(String accountNumber) {
		log.trace("Entered into getAccountByAccountNumber Method ");
		log.debug("fetching Account by account Number : {}", accountNumber);
		Account accountById = accountRepository.findAccountByAccountNumber(accountNumber);
		
		
		if(accountById != null) {
			log.info("Sucessfully retrived Account with acccount Number :{} ", accountNumber);
			AccountResponseDTO responseDTO = new AccountResponseDTO();
			BeanUtils.copyProperties(accountById, responseDTO);
			log.trace("Exiting from getAccountByAccountNumber Method");
			return responseDTO;
			
		}
		else {
			log.warn("Account not found with the accountNumber : {}", accountNumber);
			throw new AccountNumberNotFoundException("No account has been found with the accountNumber : " + accountNumber);
		}
			
		
	}

	@Override
	public AccountResponseDTO getAccountByUpiId(String upiId) {
		log.trace("Entered into getAccountByUPIId Method");
		log.debug("Fetching the AccountByUPIID : {}", upiId);
		Account accountByUpiId = accountRepository.findAccountByUpiId(upiId);
		
		if(accountByUpiId != null) {
			log.info("Sucessfully retrived Account by UpiId:{}", upiId);
			AccountResponseDTO  accountResponseDTO = new AccountResponseDTO();
			BeanUtils.copyProperties(accountByUpiId, accountResponseDTO);
			log.trace("Exiting from getAccountByUpiId Method");
			return accountResponseDTO;
		}else {
			log.warn("Account not found with the upiId :{}", upiId);
			throw new UPINotFoundException("No AccountFound with this upi Id");
		}
		
	}

	
	@Override
	public List<AccountResponseDTO> getAllAccounts() {
		log.trace("Entered into getAllaccounts Method");
		log.debug("Fetching all accounts from dataBase");
		
		List<Account> allAccounts = accountRepository.findAll();
		List<AccountResponseDTO> responseList = new ArrayList<AccountResponseDTO>();
		
		for(Account accounts : allAccounts) {
			AccountResponseDTO responseDTO = new AccountResponseDTO();
			BeanUtils.copyProperties(accounts, responseDTO);
			responseList.add(responseDTO);
		}
		log.trace("Exiting from getAllAccounts method");
		return responseList;
	}

	@Override
	public AccountResponseDTO updateAccount(Long accountId, UpdateAccountRequestDTO accountRequestDTO) {
		log.trace("Entering into updateAccount method");
		log.debug("Retriving accountData from the database");
		
		Account account = accountRepository.findById(accountId).orElseThrow(() -> {
			log.warn("no Account Have found with the accountID :{}", accountId);
			throw new AccountNotFoundException("NoAccount Have found with provided acccountId : " + accountId);
		});
		BeanUtils.copyProperties(accountRequestDTO, account);
		
		log.info("Saving the data into the Database");
		Account savedAccounts = accountRepository.save(account);
		AccountResponseDTO accountResponseDTO = new AccountResponseDTO();
		BeanUtils.copyProperties(savedAccounts, accountResponseDTO);
		log.trace("Exiting from updateAccount Method");
		return accountResponseDTO;
	}
	@Override
	public AccountResponseDTO blockAccount(Long accountId) {
		log.trace("Entered into blockAccount Method for accountId: {}", accountId);
		log.debug("Fetching account with id: {} to block", accountId);
		
		Optional<Account> byId = accountRepository.findById(accountId);
		
		if(byId.isPresent()) {
			Account account = byId.get();
			AccountStatus accountStatus = account.getAccountStatus();
			
			// Note: Checking the retrieved account's status instead of a new uninitialized account
			if(accountStatus != null && accountStatus.equals(AccountStatus.CLOSED)) {
				log.warn("Account is already blocked with given Id: {}", accountId);
				throw new AccountStatusFoundException("Account is already blocked with given Id : " + accountId);
			} else {
				account.setAccountStatus(AccountStatus.CLOSED);
			}
			
			log.info("Saving blocked account status for accountId: {}", accountId);
			Account save = accountRepository.save(account);
			AccountResponseDTO accountResponseDTO = new AccountResponseDTO();
			BeanUtils.copyProperties(save, accountResponseDTO);
			
			log.trace("Exiting blockAccount Method");
			return accountResponseDTO;
		} else {
			log.warn("No Account Found with the id: {} for blocking", accountId);
			throw new AccountNotFoundException("No Account Found with the id : " + accountId);
		}
	}


	@Override
	public AccountResponseDTO closeAccount(Long accountId) {
		log.trace("Entered into closeAccount Method");
		log.debug("Fetching the account by accountId : {}", accountId);
		
		Optional<Account> byId = accountRepository.findById(accountId);
		Account account = new Account();
		
		if(byId.isPresent()) {
			Account account2 = byId.get();
			AccountStatus accountStatus = account2.getAccountStatus();
			if(account.getAccountStatus().equals(account2.getAccountStatus().CLOSED)) {
				log.warn("Account is already closed with givenId :{}", accountId);
				throw new AccountStatusFoundException("Account is Already Closed with the given id : " + accountId);
			}else {
				account.setAccountStatus(accountStatus.CLOSED);
			}
		}else {
			log.warn("Account not found with id :{} to close ", accountId);
			throw new AccountNotFoundException("No Account Have been Found with the id : " + accountId);
		}
		
		log.info("saved closedAccount status for accountId :{}", accountId);
		Account save = accountRepository.save(account);
		AccountResponseDTO accountResponseDTO = new AccountResponseDTO();
		BeanUtils.copyProperties(save, accountResponseDTO);
		
		log.trace("Exiting from closeAccount Method");
		return accountResponseDTO;
	}

	@Override
	public AccountResponseDTO getAccountBalance(Long accountId) {
		log.trace("Entering into getAccountBalance Method");
		log.debug("Fetching the account by accountId : {}", accountId);
		
		Account account = accountRepository.findById(accountId)
				.orElseThrow(() -> {
				log.warn("No accoiunt found with accountId :{}", accountId);
					return new AccountNotFoundException("No Accounts are found with provided account Id :" + accountId);
				});

		log.info("getting account Balance");
		BigDecimal balance = account.getBalance();
		AccountResponseDTO responseDTO = new AccountResponseDTO();
		responseDTO.setBalance(balance);
		log.trace("Exiting from getAccountBalance Method");
		return responseDTO;
	}

	@Override
	public AccountResponseDTO updateAccountBalance(Long accountId, UpdateAccountBalanceDTO accountBalanceDTO) {
		log.trace("Entering into updateAccountBalance Method");
		log.debug("Fetching the account by accountId :{}", accountId);
		
		Account account = accountRepository.findById(accountId)
					.orElseThrow(() -> {
					log.warn("No account have been found with account id:{}", accountId);
					return new AccountNotFoundException("No Account have been found with the provided accountId : " + accountId);
				});

		BeanUtils.copyProperties(accountBalanceDTO, account);
		
		log.trace("saving the updated accounts into the database");
		Account savedAccounts = accountRepository.save(account);
		AccountResponseDTO accountResponseDTO = new AccountResponseDTO();
		
		BeanUtils.copyProperties(savedAccounts, accountResponseDTO);
		
		log.trace("Exiting from updateAccountBalance Method");
		return accountResponseDTO;
	}

	@Override
	public List<AccountResponseDTO> getAccountsByCustomerId(Long customerId) {
		log.trace("Entering into getAccountsByCustomerId Method");
		log.debug("Fetching accounts for customerId : {}", customerId);
		
		List<Account> allById = accountRepository.findByCustomer_CustomerId(customerId);
		ArrayList<AccountResponseDTO> responseList = new ArrayList<AccountResponseDTO>();
		
		if(allById != null && !allById.isEmpty()) {
			log.info("Successfully retrieved {} accounts for customerId : {}", allById.size(), customerId);
			for(Account account : allById) {
				AccountResponseDTO responseDTO = new AccountResponseDTO();
				BeanUtils.copyProperties(account, responseDTO);
				responseList.add(responseDTO);
			}
		}else {
			log.warn("No accounts found with the provided customerId : {}", customerId);
			throw new AccountNotFoundException("No Account has been found with the Provided customer Id : " + customerId);
		}
		
		log.trace("Exiting from getAccountsByCustomerId Method");
		return responseList;
	}

	
}
