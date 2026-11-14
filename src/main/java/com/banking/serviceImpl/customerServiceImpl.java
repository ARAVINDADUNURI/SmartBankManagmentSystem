package com.banking.serviceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.dao.CustomerRepository;
import com.banking.dto.CustomerResponseDTO;
import com.banking.dto.RegisterRequestDTO;
import com.banking.dto.UpdateCustomerRequestDTO;
import com.banking.enums.CustomerStatus;
import com.banking.exception.CustomerNotFoundException;
import com.banking.exception.CustomerStatusFoundException;
import com.banking.model.Customer;
import com.banking.service.CustomerService;
@Service
public class customerServiceImpl implements CustomerService{
	
	@Autowired
	CustomerRepository customerRepository;

	@Override
	public CustomerResponseDTO registerCustomer(RegisterRequestDTO registerRequestDTO) {
		Customer customer = new Customer();
		customer.setFirstName(registerRequestDTO.getFirstName());
		customer.setLastName(registerRequestDTO.getLastName());;
		customer.setEmail(registerRequestDTO.getEmail());
		customer.setMobileNo(registerRequestDTO.getMobileNumber());
		customer.setPassword(registerRequestDTO.getPassword());
		customer.setDateOfBirth(registerRequestDTO.getDateOfBirth());
		customer.setGender(registerRequestDTO.getGender());
		customer.setAddress(registerRequestDTO.getAddress());
		customer.setCity(registerRequestDTO.getCity());
		customer.setState(registerRequestDTO.getState());
		customer.setAadharNumber(registerRequestDTO.getAadhaarNumber());
		customer.setPanNumber(registerRequestDTO.getPanNumber());
		customer.setRole(registerRequestDTO.getRole());
		customer.setStatus(CustomerStatus.ACTIVE);
		customer.setPinCode(registerRequestDTO.getPinCode());
		
		customer.setCreatedAt(LocalDateTime.now());
		customer.setUpdatedAt(LocalDateTime.now());
		
		Customer savedCustomers = customerRepository.save(customer);
		
		CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
		
		BeanUtils.copyProperties(savedCustomers, customerResponseDTO);
			
		return customerResponseDTO;
	}

	@Override
	public CustomerResponseDTO getCustomerById(Long customerId) {
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new CustomerNotFoundException("No Customer not found with provided id : " + customerId));
		
		CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
		
		BeanUtils.copyProperties(customer, customerResponseDTO);
		
		
		return customerResponseDTO;
	}

	@Override
	public CustomerResponseDTO getCustomerByEmail(String email) {
		
		Optional<Customer> customerByEmail = customerRepository.findByEmail(email);
		
		if(customerByEmail != null && !customerByEmail.isEmpty()) {
				CustomerResponseDTO responseDTO = new CustomerResponseDTO();
				BeanUtils.copyProperties(customerByEmail.get(), responseDTO);
				return responseDTO;
			}
			
		else {
			throw new CustomerNotFoundException("No Customer Has been found with the email you Provided : " + email);
		
		}
		
	}

	@Override
	public List<CustomerResponseDTO> getAllCustomers() {
		List<Customer> allCustomers = customerRepository.findAll();
		
		List<CustomerResponseDTO> responseList = new ArrayList<CustomerResponseDTO>();
		
		for(Customer customer : allCustomers) {
			
			CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
			
			BeanUtils.copyProperties(customer, customerResponseDTO);
			
			responseList.add(customerResponseDTO);
		}
		return responseList;
	}


	@Override
	public CustomerResponseDTO blockCustomer(Long customerId) {
		Optional<Customer> byId = customerRepository.findById(customerId);
		Customer customer = new Customer();
		
		if(byId.isPresent()) {
			Customer customer2 = byId.get();
			CustomerStatus customerStatus = customer2.getStatus();
			if(customerStatus.equals(CustomerStatus.BLOCKED)) {
				throw new CustomerStatusFoundException("The Status Of the customer is already in blocked Stage");
				
			}else {
				customer.setStatus(CustomerStatus.BLOCKED);
			}
			
		}else {
			throw new CustomerNotFoundException("Customer Not found with the provided Id :" + customerId);
		}
		Customer savedCustomer = customerRepository.save(customer);
		CustomerResponseDTO responseDTO = new CustomerResponseDTO();
		BeanUtils.copyProperties(savedCustomer, responseDTO);
		return responseDTO ;
	}

	@Override
	public CustomerResponseDTO unBlockCustomer(Long customerId) {
		Optional<Customer> byId = customerRepository.findById(customerId);
		Customer customer = new Customer();
		
		if(byId.isPresent()) {
			Customer customer2 = byId.get();
			CustomerStatus customerStatus =  customer.getStatus();
			if(customerStatus.equals(customerStatus.ACTIVE)) {
				throw new CustomerStatusFoundException("The Status of the customer is Already in Active");
			}else {
				customer.setStatus(customerStatus.ACTIVE);
			}
		}else {
			throw new CustomerNotFoundException("Customer not found with the provided Id :" + customerId);
		}
		Customer savedCustomer = customerRepository.save(customer);
		CustomerResponseDTO responseDTO = new CustomerResponseDTO();
		BeanUtils.copyProperties(savedCustomer, responseDTO);
		
		return responseDTO;
	}

	@Override
	public CustomerResponseDTO updateCustomer(Long customerId, UpdateCustomerRequestDTO customerRequestDTO) {
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new CustomerNotFoundException("No Customer have been found with provided Id : " + customerId));
		
		BeanUtils.copyProperties(customerRequestDTO, customer);
			
		  Customer updatedCustomer = customerRepository.save(customer);

		    CustomerResponseDTO responseDTO = new CustomerResponseDTO();

		    BeanUtils.copyProperties(updatedCustomer, responseDTO);
		
		return responseDTO;
	}

	@Override
	public String deleteCustomer(Long customerId) {
		if(!customerRepository.existsById(customerId)) {
			throw new CustomerNotFoundException("Customer Not Found with the given Id : " + customerId);
		}else {
			customerRepository.deleteById(customerId);
			return "Customer with id : " + customerId + " have been deleted Sucessfully..!! ";
		}
	}

	@Override
	public CustomerResponseDTO getBlockedCustomerById(Long customerId) {
		if(!customerRepository.existsById(customerId)){
			throw new CustomerNotFoundException("Customer Not Found with the given Id : " + customerId);
		}else {
			Customer byId = customerRepository.findById(customerId).get();
			if(byId.getStatus() != CustomerStatus.BLOCKED) {
				throw new CustomerStatusFoundException("Customer Account is Not Blocked ");
			}else {
				
			CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
			BeanUtils.copyProperties(byId, customerResponseDTO);
		return customerResponseDTO ;
		}
		}
	}

	@Override
	public List<CustomerResponseDTO> getAllBlockedCustomers() {
		  List<Customer> allCustomers = customerRepository.findAll();
		  
		  List<CustomerResponseDTO>responseList = new ArrayList<CustomerResponseDTO>()	;
		  for(Customer customer : allCustomers) {
			  if(customer.getStatus() != CustomerStatus.BLOCKED) {
				 throw new CustomerStatusFoundException("Customers Not Found");
			  }else {
				  CustomerResponseDTO customerResponseDTO = new  CustomerResponseDTO();
				  BeanUtils.copyProperties(customerResponseDTO, allCustomers);
				 responseList.add(customerResponseDTO);
			  }
		  }
		  return responseList;

	}

}
