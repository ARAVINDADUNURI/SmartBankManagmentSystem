package com.banking.service;

import java.util.List;

import com.banking.dto.FraudAlertRequestDTO;
import com.banking.dto.FraudAlertResponseDTO;
import com.banking.enums.RiskLevel;

public interface FraudAlertService {
	
	public FraudAlertResponseDTO createFraudAlert(FraudAlertRequestDTO alertRequestDTO);
	
	public FraudAlertResponseDTO getFraudAlertById(Long alertId);
	
	public List<FraudAlertResponseDTO> getAllFraudAlerts();
	
	public List<FraudAlertResponseDTO> getAlertsByRiskLevel(RiskLevel riskLevel);
	
	public  FraudAlertResponseDTO resolveFraurdAlert(Long alertId);


}
