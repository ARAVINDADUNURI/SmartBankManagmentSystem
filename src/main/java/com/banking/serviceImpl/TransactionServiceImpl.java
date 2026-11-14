package com.banking.serviceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.dao.AccountRepository;
import com.banking.dao.CustomerRepository;
import com.banking.dao.TransactionRepository;
import com.banking.dto.TransactionRequestDTO;
import com.banking.dto.TransactionResponseDTO;
import com.banking.enums.TransactionStatus;
import com.banking.exception.AccountNotFoundException;
import com.banking.exception.CustomerNotFoundException;
import com.banking.exception.TransactionsNotFoundException;
import com.banking.model.Account;
import com.banking.model.Customer;
import com.banking.model.Transactions;
import com.banking.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {
	
	@Autowired
	TransactionRepository transactionRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	CustomerRepository customerRepository;

	@Override
	public TransactionResponseDTO createTransaction(TransactionRequestDTO transactionRequestDTO) {
		Transactions transaction = new Transactions();
		
//		transaction.set
		
//		transaction.setTransactionStatus(transactionRequestDTO.get);
		return null;
	}

	@Override
	public TransactionResponseDTO getTransactionById(Long transactionId) {
		Transactions transactionsById = transactionRepository.findById(transactionId)
				.orElseThrow(() -> new TransactionsNotFoundException("No Transactions have been found with the provided Id : " + transactionId));
				
			TransactionResponseDTO responseDTO = new TransactionResponseDTO();
			BeanUtils.copyProperties(transactionsById, responseDTO);
			return responseDTO;
		
	}

	@Override
	public List<TransactionResponseDTO> getAllTransactions() {
		List<Transactions> allTransactions = transactionRepository.findAll();
		
		ArrayList<TransactionResponseDTO> responseList = new ArrayList<TransactionResponseDTO>();
		for(Transactions transactions : allTransactions) {
			TransactionResponseDTO responseDTO = new TransactionResponseDTO();
			BeanUtils.copyProperties(transactions, responseDTO);
			responseList.add(responseDTO);
		}
		return responseList;
	}

	@Override
	public List<TransactionResponseDTO> getTranctionsByAccountId(Long accountId) {
		Account account = accountRepository.findById(accountId)
				.orElseThrow(() -> new AccountNotFoundException("No Accounts Have been Found with provided account Id : " + accountId));
		
		ArrayList<TransactionResponseDTO> responseList = new ArrayList<TransactionResponseDTO>();
		
		List<Transactions> transactions = account.getRecivedTransaction();
		if(transactions.isEmpty()) {
			throw new TransactionsNotFoundException("No transactions have been found for this account : " + accountId);
		}
		
		for(Transactions list : transactions) {
			TransactionResponseDTO responseDTO = new TransactionResponseDTO();
			BeanUtils.copyProperties(list, responseDTO);
			responseList.add(responseDTO);
			
		}
		return responseList;

		
	}

	@Override
	public List<TransactionResponseDTO> getTransactionsByCustomerId(Long customerId) {
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new CustomerNotFoundException("No Customer have been found with the provided customerId : " + customerId));
		ArrayList<TransactionResponseDTO> responseList = new ArrayList<TransactionResponseDTO>();
		
		List<Account> accountsList = customer.getAccounts();
		
		if(accountsList.isEmpty()) {
			throw new TransactionsNotFoundException("No Transactions are found for this account with provided customerId : " + customerId);
			
		}
		for(Account list : accountsList) {
			List<Transactions> recivedTransaction = list.getRecivedTransaction();
			List<Transactions> sentTransactions = list.getSentTransactions();
			
			for(Transactions transactionsRecived : recivedTransaction) {
					TransactionResponseDTO responseDTO = new TransactionResponseDTO();
					BeanUtils.copyProperties(transactionsRecived, responseDTO);
					responseList.add(responseDTO);
				}
			for(Transactions transactionsSent : sentTransactions) {
				TransactionResponseDTO responseDTO = new TransactionResponseDTO();
				BeanUtils.copyProperties(transactionsSent, responseDTO);
				responseList.add(responseDTO);
			}
			
		}
		
		return responseList;
	}

	

	@Override
	public List<TransactionResponseDTO> getTransactionsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
		
		List<Transactions> transactionsByDateRange = transactionRepository.findByTransactionDateBetween(startDate, endDate);
		ArrayList<TransactionResponseDTO> responseList = new ArrayList<TransactionResponseDTO>();
		
		if(transactionsByDateRange.isEmpty()) {
			throw new TransactionsNotFoundException("No Transactions Have been Found with the provided start&end dates : " + startDate + endDate);
		}
		
		for(Transactions transactions : transactionsByDateRange) {
			
			TransactionResponseDTO responseDTO = new TransactionResponseDTO();
			BeanUtils.copyProperties(transactions, responseDTO);
			responseList.add(responseDTO);
		}
		
		return responseList;
	}

	@Override
	public List<TransactionResponseDTO> getTransactionsByTransactionStatus(TransactionStatus transactionStatus) {
		
		List<Transactions> transactionsByTransactionStatus = transactionRepository.findTransactionsByTransactionStatus(transactionStatus);
		ArrayList<TransactionResponseDTO> responseList = new ArrayList<TransactionResponseDTO>();
		if(transactionsByTransactionStatus.isEmpty()) {
			throw new TransactionsNotFoundException("No Transactions have been found with the provided transactionStatus : " + transactionStatus);
		}
		
		for(Transactions transactions : transactionsByTransactionStatus) {
			
			TransactionResponseDTO responseDTO = new TransactionResponseDTO();
			BeanUtils.copyProperties(transactions, responseDTO);
			responseList.add(responseDTO);
		}
		return responseList;
	}

}
