package com.banking.service;

import java.util.ArrayList;
import java.util.List;

import com.banking.dto.CustomerResponseDTO;
import com.banking.dto.RegisterRequestDTO;
import com.banking.dto.UpdateCustomerRequestDTO;

public interface CustomerService {
	
	public CustomerResponseDTO registerCustomer(RegisterRequestDTO registerRequestDTO);
	
	public CustomerResponseDTO getCustomerById(Long customerId);
	
	public ArrayList<CustomerResponseDTO> getCustomerByEmail(String email);
	
	public List<CustomerResponseDTO> getAllCustomers();
	
	public CustomerResponseDTO updateCustomer(Long customerId, UpdateCustomerRequestDTO customerRequestDTO);
	
	public CustomerResponseDTO blockCustomer(Long customerId);
	
	public CustomerResponseDTO unBlockCustomer(Long customerId);
	
	

}
