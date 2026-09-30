package com.banking.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.model.Savings;

public interface SavingsRepository extends JpaRepository<Savings, Long> {

}
