package com.banking.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

	List<Customer> findCustomerByEmail(String email);

}
