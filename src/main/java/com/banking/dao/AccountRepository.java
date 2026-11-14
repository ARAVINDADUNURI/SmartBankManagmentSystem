package com.banking.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.model.Account;

public interface AccountRepository extends JpaRepository<Account,Long> {
	
	Account findAccountByAccountNumber(String accountNumber);
	
	Account findAccountByUpiId(String upiId);
	
//	List<Account> getAccountsByCustomerId(Long customerId);
	
    List<Account> findByCustomer_CustomerId(Long customerId);

}
