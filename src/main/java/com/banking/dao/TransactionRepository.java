package com.banking.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {

}
