package com.banking.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.model.Loan;

public interface LoanRepository extends JpaRepository<Loan,Long> {

}
