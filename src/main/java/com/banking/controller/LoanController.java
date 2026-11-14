package com.banking.controller;

import java.util.List;

import org.hibernate.engine.spi.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.dto.LoanApplicationRequestDTO;
import com.banking.dto.LoanResponseDTO;
import com.banking.dto.UpdateLoanRequestDTO;
import com.banking.enums.LoanStatus;
import com.banking.model.Loan;
import com.banking.service.LoanService;
@RestController
@RequestMapping("/loan")
public class LoanController {
	
	/*
	 * private LoanService loanService; LoanController(LoanService loanService){
	 * this.loanService = loanService; }
	 */
	@Autowired
	LoanService loanService;
	
	@GetMapping("/loanbyId/{loanId}")
	public ResponseEntity<LoanResponseDTO> getLoanById(@PathVariable Long loanId) {
		LoanResponseDTO loanById = loanService.getLoanById(loanId);
		return ResponseEntity.ok(loanById);
	}
	
	@GetMapping("/getAllLoans")
	public ResponseEntity getAllLoans() {
		List<LoanResponseDTO> allLoans = loanService.getAllLoans();
		return ResponseEntity.ok(allLoans);
	}
	
	@GetMapping("/getLoanByCustomerId/{customerId}")
	public ResponseEntity getLoanByCustomerId(@PathVariable Long customerId) {
		List<LoanResponseDTO> loansByCustomerId = loanService.getLoansByCustomerId(customerId);
		return ResponseEntity.ok(loansByCustomerId);
	}
	
	@PostMapping("/approveLoan/{loanId}")
	public ResponseEntity approveLoan(@PathVariable Long loanId) {
		LoanResponseDTO approveLoan = loanService.approveLoan(loanId);
		return ResponseEntity.ok(approveLoan);
	}
	
	@PostMapping("/rejectLoan/{loanId}")
	public ResponseEntity<LoanResponseDTO> rejectLoan(@PathVariable Long loanId){
		LoanResponseDTO rejectLoan = loanService.rejectLoan(loanId);
		return ResponseEntity.ok(rejectLoan);
	}
	
	@PutMapping("/update/{loanId}")
	public LoanResponseDTO updateLoanStatus(@RequestBody UpdateLoanRequestDTO updateLoanRequestDTO, @PathVariable Long loanId) {
		return loanService.updateLoanStatus(loanId, updateLoanRequestDTO);
		
	}
	
	@GetMapping("/loansByStatus/{status}")
	public ResponseEntity getLoansByStatus(@PathVariable LoanStatus status) {
		List<LoanResponseDTO> loanByStatus = loanService.getLoanByStatus(status);
		return ResponseEntity.ok(loanByStatus);
	}
	
	@PutMapping("/dispurse/{loanId}")
	public ResponseEntity<LoanResponseDTO> dispurseLoan(@PathVariable Long loanId) {
		LoanResponseDTO disbursedLoan = loanService.dispurseLoan(loanId);
		return ResponseEntity.ok(disbursedLoan);
	}
	
	@PostMapping("/appalyLoan/{customerId}")
	public ResponseEntity<LoanResponseDTO> applayLoan(@RequestBody LoanApplicationRequestDTO loanApplicationRequestDTO, @PathVariable Long customerId){
		LoanResponseDTO applayLoan = loanService.applayLoan(loanApplicationRequestDTO, customerId);
		return ResponseEntity.ok(applayLoan);
		
	}

		
	
}
