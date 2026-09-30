package com.banking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.dto.CustomerResponseDTO;
import com.banking.dto.RegisterRequestDTO;
import com.banking.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerDetailsController {
	
	@Autowired
	CustomerService customerService;
	
	@PostMapping("/add")
	public ResponseEntity addCustomer(@RequestBody RegisterRequestDTO registerRequestDTO ) {
		CustomerResponseDTO customerResponseDTO = customerService.add
		
	}

}
