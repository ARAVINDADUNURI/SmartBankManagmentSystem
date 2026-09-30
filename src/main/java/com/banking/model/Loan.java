package com.banking.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.OptimisticLock;

import com.banking.enums.LoanStatus;
import com.banking.enums.LoanType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "loans")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Loan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "loan_id")
	private Long loanId;
	
	@Column(name = "loan_type")
	private LoanType loanType;
	
	@Column(name = "requested_amount")
	private BigDecimal requestedAmount;
	
	@Column(name = "approved_amount")
	private BigDecimal approvedAmount;
	
	@Column(name = "tenure_months")
	private Integer trenureMonths;
	
	@Column(name = "intrest_rate")
	private BigDecimal intrestRate;
	
	@Column(name = "purpose")
	private String purpose;
	
	@Column(name = "loan_status")
	private LoanStatus loanStatus;
	
	@Column(name = "applied_date")
	private LocalDateTime appliedDate;
	
	@Column(name = "review_date")
	private LocalDateTime reviewedDate;
	
	@Column(name = "admin_marks")
	private String adminRemarks;
	
//	Mappings
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Customer customer;
	
	@Column(name = " account")
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Account account;

	public Loan(LoanType loanType, BigDecimal requestedAmount, BigDecimal approvedAmount, Integer trenureMonths,
			BigDecimal intrestRate, String purpose, LoanStatus loanStatus, LocalDateTime appliedDate,
			LocalDateTime reviewedDate, String adminRemarks, Customer customer, Account account) {
		super();
		this.loanType = loanType;
		this.requestedAmount = requestedAmount;
		this.approvedAmount = approvedAmount;
		this.trenureMonths = trenureMonths;
		this.intrestRate = intrestRate;
		this.purpose = purpose;
		this.loanStatus = loanStatus;
		this.appliedDate = appliedDate;
		this.reviewedDate = reviewedDate;
		this.adminRemarks = adminRemarks;
		this.customer = customer;
		this.account = account;
	}
	
	
	

/*	public Loan(Customer customer, Account account, LoanType loanType, BigDecimal requestedAmount,
			BigDecimal approvedAmount, Integer trenureMonths, BigDecimal intrestRate, String purpose,
			LoanStatus loanStatus, LocalDateTime appliedDate, LocalDateTime reviewedDate, String adminRemarks) {
		super();
		this.customer = customer;
		this.account = account;
		this.loanType = loanType;
		this.requestedAmount = requestedAmount;
		this.approvedAmount = approvedAmount;
		this.trenureMonths = trenureMonths;
		this.intrestRate = intrestRate;
		this.purpose = purpose;
		this.loanStatus = loanStatus;
		this.appliedDate = appliedDate;
		this.reviewedDate = reviewedDate;
		this.adminRemarks = adminRemarks;
	}
	*/
	

}
