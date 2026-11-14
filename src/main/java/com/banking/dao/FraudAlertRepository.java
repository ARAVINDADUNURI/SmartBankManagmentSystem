package com.banking.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.enums.RiskLevel;
import com.banking.model.FraudAlert;

public interface FraudAlertRepository extends JpaRepository<FraudAlert,Long>{
	
	List<FraudAlert> findByRiskLevel(RiskLevel level);

}
