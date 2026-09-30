package com.banking.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.banking.enums.GoalStatus;

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
@Table(name = "savings")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Savings {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "goal_id")
	public Long goalId;
	
	@Column(name = "goal_name")
	private String goalName;
	
	@Column(name = "targeted_amount")
	private BigDecimal targetAmount;
	
	@Column(name = "current_amount")
	private BigDecimal currentAmount;
	
	@Column(name = " targeted_date")
	private LocalDate targetDate;
	
	@Column(name = "goal_status")
	private GoalStatus status;
	
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;
	
	@Column(name = "customer")
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public Customer customer;

	public Savings(Customer customer, String goalName, BigDecimal targetAmount, BigDecimal currentAmount,
			LocalDate targetDate, GoalStatus status, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.customer = customer;
		this.goalName = goalName;
		this.targetAmount = targetAmount;
		this.currentAmount = currentAmount;
		this.targetDate = targetDate;
		this.status = status;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	
	
}
