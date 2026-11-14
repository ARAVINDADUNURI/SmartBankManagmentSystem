package com.banking.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

	Optional<Customer> findByEmail(String email); 
}
