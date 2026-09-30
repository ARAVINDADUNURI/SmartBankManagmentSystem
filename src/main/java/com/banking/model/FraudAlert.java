package com.banking.model;

import java.time.LocalDateTime;

import com.banking.enums.RiskLevel;

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
@Table(name = "fraud_alert")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FraudAlert {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "alert_id")
	private Long alertId;
	
	@Column(name = "reason")
	private String reason;
	
	@Column(name = "risk_level")
	private RiskLevel riskLevel;
	
	@Column(name = "detected_at")
	private LocalDateTime detectedAt;
	
	@Column(name = "admin_remarks")
	private String adminRemarks;
	
	@Column(name = "resolved_at")
	private LocalDateTime resolvedAt;
	
	@Column(name = "transaction")
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private Transaction transaction;
	
	@Column(name = "customer")
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private Customer customer;

	public FraudAlert(Transaction transaction, Customer customer, String reason, RiskLevel riskLevel,
			LocalDateTime detectedAt, String adminRemarks, LocalDateTime resolvedAt) {
		super();
		this.transaction = transaction;
		this.customer = customer;
		this.reason = reason;
		this.riskLevel = riskLevel;
		this.detectedAt = detectedAt;
		this.adminRemarks = adminRemarks;
		this.resolvedAt = resolvedAt;
	}
	
	
	

}
