package com.banking.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.banking.enums.AccountStatus;
import com.banking.enums.AccountType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "account_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "account_id")
	private Long accountId;
	
	@Column(name = "account_Number", unique = true)
	private String accountNumber;
	
	@Column(name = "account_Type")
	private AccountType accountType;
	
	@Column(name = "balance")
	private BigDecimal balance;
	
	@Column(name = "upiId", unique = true)
	private String upiId;
	
	@Column(name = "branch_Name")
	private String branchName;
	
	@Column(name = "ifsc")
	private String ifscCode;
	
	@Column(name = "account_Status")
	private AccountStatus accountStatus;
	
	@Column(name = "created_At")
	private LocalDateTime createdAt;
	
	@Column(name = "updatedAt")
	private LocalDateTime updatedAt;

//	Mappings
	
	@Column(name = "customer")
	@ManyToOne(cascade =  CascadeType.ALL, fetch = FetchType.EAGER)
	private Customer customer;
	
	@OneToMany(cascade =  CascadeType.ALL, fetch = FetchType.EAGER)
	private Transaction sentTransactions;
	
	@OneToMany(cascade =  CascadeType.ALL, fetch = FetchType.EAGER)
	private Transaction recivedTransaction;
	
	
/*	public Account(String accountNumber, Customer customer, AccountType accountType, BigDecimal balance, 
			String upiId, String branchName, String ifscCode, LocalDateTime createdAt, LocalDateTime updatedAt ) {
		super();
		this.accountNumber = accountNumber;
		this.customer = customer;
		this.accountType = accountType;
		this.balance = balance;
		this.upiId = upiId;
		this.branchName = branchName;
		this.ifscCode = ifscCode;
		this.accountStatus = accountStatus;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		
	}*/

	public Account(String accountNumber, AccountType accountType, BigDecimal balance, String upiId, String branchName,
			String ifscCode, AccountStatus accountStatus, LocalDateTime createdAt, LocalDateTime updatedAt,
			Customer customer, Transaction sentTransactions, Transaction recivedTransaction) {
		super();
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.balance = balance;
		this.upiId = upiId;
		this.branchName = branchName;
		this.ifscCode = ifscCode;
		this.accountStatus = accountStatus;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.customer = customer;
		this.sentTransactions = sentTransactions;
		this.recivedTransaction = recivedTransaction;
	}



	

}
