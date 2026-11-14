package com.banking.dao;

import java.util.List;

import org.hibernate.engine.spi.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.enums.LoanStatus;
import com.banking.model.Loan;

public interface LoanRepository extends JpaRepository<Loan,Long> {
	
	List<Loan> findByLoanStatus(LoanStatus loanStatus);

	List<Loan> findByCustomer_CustomerId(Long customerId);
}
