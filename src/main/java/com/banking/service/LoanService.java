package com.banking.service;

import java.util.List;

import com.banking.dto.LoanApplicationRequestDTO;
import com.banking.dto.LoanResponseDTO;
import com.banking.dto.UpdateLoanRequestDTO;
import com.banking.enums.LoanStatus;

public interface LoanService {
	
	public LoanResponseDTO applayLoan(LoanApplicationRequestDTO applicationRequestDTO, Long customerId);
	
	public LoanResponseDTO getLoanById(Long loanId);
	
	public List<LoanResponseDTO> getAllLoans();
	;
	public List<LoanResponseDTO> getLoansByCustomerId(Long customerId);
	
	public LoanResponseDTO approveLoan(Long loanId);
	
	public LoanResponseDTO rejectLoan(Long loanId);
	
	public LoanResponseDTO updateLoanStatus(Long loanId, UpdateLoanRequestDTO updateLoanRequestDTO );
	
	public List<LoanResponseDTO> getLoanByStatus(LoanStatus loanStatus);
	
	public LoanResponseDTO dispurseLoan(Long loanId);
}
