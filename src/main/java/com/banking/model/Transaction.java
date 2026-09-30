package com.banking.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.transaction.TransactionStatus;

import com.banking.enums.FraudStatus;
import com.banking.enums.TransactionType;

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
@Table(name = "transactions")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "transaction_id's")
	private Long transactionId;
	
	@Column(name = "transaction_reference", unique = true)
	private String transactionReference;
	
	@Column(name = "amount")
	private BigDecimal amount;
	
	@Column(name = "transaction_type")
	private TransactionType transactionType;
	
	@Column(name = "transaction_status")
	private TransactionStatus transactionStatus;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "transaction_date")
	private LocalDateTime transactionDate;
	
	@Column(name = "fraud_status")
	private FraudStatus fraudStatus;
	
//	Mappings
	
	@Column(name = "sender_account")
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Account senderAccount;
	
	@Column(name = "reciver_account")
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Account reciverAccount;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private FraudAlert fraudAlert;
	
	public Transaction(String transactionReference, BigDecimal amount, TransactionType transactionType,
			TransactionStatus transactionStatus, String description, LocalDateTime transactionDate,
			FraudStatus fraudStatus, Account senderAccount, Account reciverAccount, FraudAlert fraudAlert) {
		super();
		this.transactionReference = transactionReference;
		this.amount = amount;
		this.transactionType = transactionType;
		this.transactionStatus = transactionStatus;
		this.description = description;
		this.transactionDate = transactionDate;
		this.fraudStatus = fraudStatus;
		this.senderAccount = senderAccount;
		this.reciverAccount = reciverAccount;
		this.fraudAlert = fraudAlert;
	}
	
		
	/*public Transaction(String transactionReference, Account senderAccount, Account reciverAccount, BigDecimal amount,
			TransactionType transactionType, TransactionStatus transactionStatus, String description,
			LocalDateTime transactionDate, FraudStatus fraudStatus) {
		super();
		this.transactionReference = transactionReference;
		this.senderAccount = senderAccount;
		this.reciverAccount = reciverAccount;
		this.amount = amount;
		this.transactionType = transactionType;
		this.transactionStatus = transactionStatus;
		this.description = description;
		this.transactionDate = transactionDate;
		this.fraudStatus = fraudStatus;
	}

*/	
	

}
