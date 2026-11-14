package com.banking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.dto.CustomerResponseDTO;
import com.banking.dto.RegisterRequestDTO;
import com.banking.dto.UpdateCustomerRequestDTO;
import com.banking.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerDetailsController {
	
	@Autowired
	CustomerService customerService;
	
	@PostMapping("/add")
	public ResponseEntity addCustomer(@RequestBody RegisterRequestDTO registerRequestDTO ) {
		CustomerResponseDTO customerResponseDTO = customerService.registerCustomer(registerRequestDTO);
		return new ResponseEntity(customerResponseDTO, HttpStatus.CREATED);
		
	}
	
	@GetMapping("/byId/{customerId}")
	public ResponseEntity getCustomerById(@PathVariable Long customerId) {
		CustomerResponseDTO customerById = customerService.getCustomerById(customerId);
		
		return ResponseEntity.ok(customerById);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity getAllCustomers() {
		List<CustomerResponseDTO>allCustomers = customerService.getAllCustomers();
		return ResponseEntity.ok(allCustomers);
	}
	
	@GetMapping("/getByEmail/{email}")
	public ResponseEntity getCustomerByEmailId(@PathVariable String email) {
		CustomerResponseDTO customerByEmail = customerService.getCustomerByEmail(email);
		return ResponseEntity.ok(customerByEmail);
	}
	
	@DeleteMapping("/delete/{customerId}")
	public ResponseEntity deleteCustomerById(@PathVariable Long customerId) {
		String customerById = customerService.deleteCustomer(customerId);
		return ResponseEntity.ok(customerById);
		
	}
	
	@PutMapping("/update/{customerId}")
	public CustomerResponseDTO updateCustomerById(@PathVariable Long customerId, @RequestBody UpdateCustomerRequestDTO customerRequestDTO ) {
		return customerService.updateCustomer(customerId, customerRequestDTO);
	}
	
	@GetMapping("/blocked/{customerId}")
	public ResponseEntity getBlockedCustomerById(@PathVariable Long customerId) {
		CustomerResponseDTO customerById = customerService.getBlockedCustomerById(customerId);
		return ResponseEntity.ok(customerById);
	}
	
	@GetMapping("/getAllBlocked")
	public ResponseEntity getAllBlockedCustomers() {
		List<CustomerResponseDTO>allblockedCustomers = customerService.getAllBlockedCustomers();
		return ResponseEntity.ok(allblockedCustomers);
	}

}
