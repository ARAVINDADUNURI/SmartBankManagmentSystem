package com.banking.serviceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.dao.CustomerRepository;
import com.banking.dao.SavingsRepository;
import com.banking.dto.SavingsRequestDTO;
import com.banking.dto.SavingsResponseDTO;
import com.banking.exception.CustomerNotFoundException;
import com.banking.exception.InsufficientBalanceException;
import com.banking.exception.NoSavingsAccountFoundException;
import com.banking.model.Customer;
import com.banking.model.Savings;
import com.banking.service.SavingsService;

@Service
public class SavingsServiceImpl implements SavingsService {
	
	@Autowired
	SavingsRepository savingsRepository;
	
	@Autowired
	CustomerRepository customerRepository;

	@Override
	public SavingsResponseDTO createSavingsAccount(SavingsRequestDTO savingsRequestDTO) {
		Savings savings = new Savings();
		
		savings.setGoalName(savingsRequestDTO.getGoalName());
		savings.setCurrentAmount(savings.getCurrentAmount());
		savings.setTargetAmount(savingsRequestDTO.getTargatedAmount());
		savings.setTargetDate(savingsRequestDTO.getTargetDate());
		
		Savings savedAccounts = savingsRepository.save(savings);
		
		SavingsResponseDTO savingsResponseDTO = new SavingsResponseDTO();
		BeanUtils.copyProperties(savedAccounts, savingsResponseDTO);
		return savingsResponseDTO;
	}

	@Override
	public SavingsResponseDTO getSavingsByAccountId(Long goalId) {
		Optional<Savings> byId = savingsRepository.findById(goalId);
		if(!byId.isPresent()) {
			throw new NoSavingsAccountFoundException("No Savings account have benn found with the provided gaolId : " + goalId);
		}else {
			
			Savings savings = byId.get();
			
			SavingsResponseDTO responseDTO = new SavingsResponseDTO();
			BeanUtils.copyProperties(savings, responseDTO);
			return responseDTO;
		}
	
	}

	@Override
	public List<SavingsResponseDTO> getAllSavingsAccount() {
	List<Savings> allSavingsAccounts = savingsRepository.findAll();
	
	ArrayList<SavingsResponseDTO> responseList = new ArrayList<SavingsResponseDTO>();
	for(Savings savingsAccounts : allSavingsAccounts) {
		
		SavingsResponseDTO responseDTO = new SavingsResponseDTO();
		
		BeanUtils.copyProperties(savingsAccounts, responseDTO);
		responseList.add(responseDTO);
	}
		return responseList;
	}

	
	@Override
	public SavingsResponseDTO getSavingsAccountByCustomerId(Long customerId) {
		Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException("No customer record have been found with the provided CustomerId : " + customerId));
		
//		if(!byId.isPresent()) {
//			throw new CustomerNotFoundException("No customer record have been found with the provided CustomerId : " + customerId);
//		}
//			Customer customer = byId;
		
			List<Savings> savings = customer.getSavings();
			
			if(savings == null) {
				throw new NoSavingsAccountFoundException("No Savings account have been found with the provided Customer Id : " + customerId);
			}else {
				
				SavingsResponseDTO responseDTO = new SavingsResponseDTO();
				BeanUtils.copyProperties(savings, responseDTO);
				return responseDTO;
			}

		
		
	}

	
	@Override
	public SavingsResponseDTO depositAmount(Long accountId, BigDecimal amount) {
		Savings savings = savingsRepository.findById(accountId)
				.orElseThrow(() -> new NoSavingsAccountFoundException("No Savings account found with the provided account ID: " + accountId));
		
		if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
			throw new IllegalArgumentException("Deposit amount must be greater than zero");
		}
		
		BigDecimal currentAmount = savings.getCurrentAmount() != null ? savings.getCurrentAmount() : BigDecimal.ZERO;
		savings.setCurrentAmount(currentAmount.add(amount));
		
		Savings updatedSavings = savingsRepository.save(savings);
		
		SavingsResponseDTO responseDTO = new SavingsResponseDTO();
		BeanUtils.copyProperties(updatedSavings, responseDTO);
		return responseDTO;
	}


	@Override
	public SavingsResponseDTO withDrawAmount(Long accountId, BigDecimal amount) {
		Savings savings = savingsRepository.findById(accountId)
				.orElseThrow(() -> new NoSavingsAccountFoundException("No Savings account found with the provided Id : " + accountId));
		if(amount == null || amount.compareTo(BigDecimal.ZERO) <= 0 ) {
			throw new IllegalArgumentException("WithDraw amount must be greater tha Zero ");
			
		}
		BigDecimal currentAmount =   savings.getCurrentAmount() != null ? savings.getCurrentAmount() : BigDecimal.ZERO;
		int compareTo = amount.compareTo(currentAmount);
		if(compareTo == 1) {
			throw new  InsufficientBalanceException("No Sufficient balance is found");
			
		}
		savings.setCurrentAmount(currentAmount.subtract(amount));
		
		Savings updatedSavings = savingsRepository.save(savings);
		
		SavingsResponseDTO responseDTO = new SavingsResponseDTO();
		BeanUtils.copyProperties(updatedSavings, responseDTO);
		return responseDTO;
	}

}
