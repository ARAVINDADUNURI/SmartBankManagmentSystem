package com.banking.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.model.Account;

public interface AccountRepository extends JpaRepository<Account,Long> {

}
