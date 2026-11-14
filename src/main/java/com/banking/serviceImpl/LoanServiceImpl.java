package com.banking.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.engine.spi.Status;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.dao.CustomerRepository;
import com.banking.dao.LoanRepository;
import com.banking.dto.LoanApplicationRequestDTO;
import com.banking.dto.LoanResponseDTO;
import com.banking.dto.UpdateLoanRequestDTO;
import com.banking.enums.LoanStatus;
import com.banking.exception.CustomerNotFoundException;
import com.banking.exception.LoanIsNotApprovedException;
import com.banking.exception.LoanIsNotRejectedException;
import com.banking.exception.LoanNotFoundException;
import com.banking.model.Customer;
import com.banking.model.Loan;
import com.banking.service.LoanService;

@Service
public class LoanServiceImpl implements LoanService {
	
	@Autowired
	LoanRepository loanRepository;
	
	@Autowired
	CustomerRepository customerRepository;

	@Override
	public LoanResponseDTO getLoanById(Long loanId) {
		Optional<Loan> byId = loanRepository.findById(loanId);
		Loan loan = byId.get();
		
		if(!byId.isPresent()) {
			throw new LoanNotFoundException("Loan is not found with the provided Id : " + loanId);
			
		}
		LoanResponseDTO loanResponseDTO = new LoanResponseDTO();
		BeanUtils.copyProperties(loan, loanResponseDTO);
		return loanResponseDTO;
	}

	@Override
	public List<LoanResponseDTO> getAllLoans() {
		
		List<Loan> allLoans = loanRepository.findAll();
		
		List<LoanResponseDTO> responseList = new ArrayList<LoanResponseDTO>();
		
		for(Loan loan : allLoans) {
			LoanResponseDTO responseDTO = new LoanResponseDTO();
			BeanUtils.copyProperties(loan, responseList);
			responseList.add(responseDTO);
		}
		return responseList;
	}

	@Override
	public List<LoanResponseDTO> getLoansByCustomerId(Long customerId) {
	Optional<Customer> byId = customerRepository.findById(customerId);
	
	if(!byId.isPresent()) {
		throw new CustomerNotFoundException("No Customer found with the provided CustomerId : " + customerId);
		
	}else {
		List<Loan> loansByCustomerId = loanRepository.findByCustomer_CustomerId(customerId);
		ArrayList<LoanResponseDTO> responseList = new ArrayList<LoanResponseDTO>();
		for(Loan loans : loansByCustomerId) {
			LoanResponseDTO loanResponseDTO = new LoanResponseDTO();
			BeanUtils.copyProperties(loans,loanResponseDTO);
			responseList.add(loanResponseDTO);
		}
		return responseList;
	}
		
}

	@Override
	public LoanResponseDTO approveLoan(Long loanId) {
		Optional<Loan> byId = loanRepository.findById(loanId);
		if(!byId.isPresent()) {
			throw new LoanNotFoundException("No Loan Found with the provided Id : " + loanId);
		}else {
			
			Loan loan = byId.get();
			LoanStatus loanStatus = loan.getLoanStatus();
			if(loanStatus == LoanStatus.PENDING) {
				loan.setLoanStatus(loanStatus.APPROVED);
				Loan savedLoan = loanRepository.save(loan);
				LoanResponseDTO loanResponseDTO = new LoanResponseDTO();
				BeanUtils.copyProperties(savedLoan, loanResponseDTO);
				return loanResponseDTO;
			}else {
				throw new LoanIsNotApprovedException("The Applied Loan Is not approved yet ");
			}
		}
	
		
	}

	@Override
	public LoanResponseDTO rejectLoan(Long loanId) {
		Optional<Loan> byId = loanRepository.findById(loanId);
		
		if(!byId.isPresent()) {
			throw new LoanNotFoundException("Loan is not found with the provided loanId : " + loanId);
			
		}else {
			Loan loan = byId.get();
			LoanStatus loanStatus = loan.getLoanStatus();
			if(loanStatus == LoanStatus.PENDING) {
				loan.setLoanStatus(loanStatus.REJECTED);
				Loan savedLoan = loanRepository.save(loan);
				LoanResponseDTO loanResponseDTO = new LoanResponseDTO();
				BeanUtils.copyProperties(savedLoan, loanResponseDTO);
				return loanResponseDTO;
			}else {
				throw new LoanIsNotRejectedException("loan is not rejected with the id : " + loanId);
			}
		}
		
	}

	@Override
	public LoanResponseDTO updateLoanStatus(Long loanId, UpdateLoanRequestDTO updateLoanRequestDTO) {
		Optional<Loan> byId = loanRepository.findById(loanId);
		
		if(!byId.isPresent()) {
			throw new LoanNotFoundException("No Loan Is found with the provided id : " + loanId);
		}else {
			BeanUtils.copyProperties(updateLoanRequestDTO, byId);
			LoanResponseDTO responseDTO = new LoanResponseDTO();
			BeanUtils.copyProperties(byId, responseDTO);
			
			return responseDTO;
		
		}
	}

	@Override
	public List<LoanResponseDTO> getLoanByStatus(LoanStatus loanStatus) {
		List<Loan> byStatus = loanRepository.findByLoanStatus(loanStatus);
		
		if(byStatus == null || byStatus.isEmpty()) {
			throw new LoanNotFoundException("No Loans are Found By the Status : " + loanStatus);
		}else{
			ArrayList<LoanResponseDTO> responseList = new ArrayList<LoanResponseDTO>();
			for(Loan loanStatus1 : byStatus) {
				LoanResponseDTO responseDTO = new LoanResponseDTO();
				BeanUtils.copyProperties(loanStatus1, responseList);
				responseList.add(responseDTO);
			}
			return responseList;
		}
		
	}

	@Override
	public LoanResponseDTO dispurseLoan(Long loanId) {
		Optional<Loan> byId = loanRepository.findById(loanId);
		if (!byId.isPresent()) {
			throw new LoanNotFoundException("No Loan Found with the provided Id : " + loanId);
		} else {
			Loan loan = byId.get();
			LoanStatus loanStatus = loan.getLoanStatus();
			
			// Check if the loan is approved and ready for disbursement
			if (loanStatus == LoanStatus.APPROVED) {
				loan.setLoanStatus(LoanStatus.DISBURSED); // or appropriate enum value
				Loan savedLoan = loanRepository.save(loan);
				LoanResponseDTO loanResponseDTO = new LoanResponseDTO();
				BeanUtils.copyProperties(savedLoan, loanResponseDTO);
				return loanResponseDTO;
			} else {
				throw new LoanIsNotApprovedException("The loan must be approved before it can be disbursed.");
			}
		}
	
	}

	@Override
	public LoanResponseDTO applayLoan(LoanApplicationRequestDTO applicationRequestDTO, Long customerId) {
		Optional<Customer> byId = customerRepository.findById(customerId);
		if(!byId.isPresent()) {
			throw new CustomerNotFoundException("Customer Not Found with Provided Id : " + customerId);
		}else {
			 Customer customer = byId.get();
			 Loan loan = new Loan();
			 BeanUtils.copyProperties(applicationRequestDTO, loan);
			 
			 loan.setLoanStatus(LoanStatus.PENDING);
			 loan.setCustomer(customer);
			 
			 Loan savedLoan = loanRepository.save(loan);
			 LoanResponseDTO loanResponseDTO = new LoanResponseDTO();
			 BeanUtils.copyProperties(savedLoan, loanResponseDTO);
			 return loanResponseDTO;
		}
		
	}

}
