package com.banking.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.enums.TransactionStatus;
import com.banking.model.Transactions;

public interface TransactionRepository extends JpaRepository<Transactions,Long> {
	
	List<Transactions> findTransactionsByTransactionStatus(TransactionStatus transactionStatus);
	
	List<Transactions> findByTransactionDateBetween(
		    LocalDateTime startTime, LocalDateTime endTime);
}
