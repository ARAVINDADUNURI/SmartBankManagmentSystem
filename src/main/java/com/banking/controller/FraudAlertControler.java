package com.banking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.dao.FraudAlertRepository;
import com.banking.dto.FraudAlertRequestDTO;
import com.banking.dto.FraudAlertResponseDTO;import com.banking.dto.LoanResponseDTO;
import com.banking.enums.RiskLevel;
import com.banking.service.FraudAlertService;

import io.micrometer.core.ipc.http.HttpSender.Response;

@RestController
@RequestMapping("/fraudAlert")
public class FraudAlertControler {
		
/*	private final FraudAlertRepository fraudAlertRepository;
	FraudAlertControler(FraudAlertRepository fraudAlertRepository) {
		this.fraudAlertRepository = fraudAlertRepository;
	}     (CONSTRUCTOR LEVEL OBJECT CREATION WITHOUT AUTOWIRED SIMPLE BEAN CREATION OR OBJECT CREATION A)*/
	
	@Autowired
	FraudAlertService fraudAlertService;
	
	@PostMapping("/addFraud")
	public ResponseEntity<FraudAlertResponseDTO> createFraudAlert(@RequestBody FraudAlertRequestDTO alertRequestDTO) {
		FraudAlertResponseDTO fraudAlert = fraudAlertService.createFraudAlert(alertRequestDTO);
		return new ResponseEntity(alertRequestDTO, HttpStatus.CREATED);
		
	}
	
	@GetMapping("/getById/{alretId}")
	public ResponseEntity<FraudAlertResponseDTO> getFraudAlertById(@PathVariable Long alertId) {	
		FraudAlertResponseDTO fraudAlertById = fraudAlertService.getFraudAlertById(alertId);
		return ResponseEntity.ok(fraudAlertById);
		
	}
	
	@GetMapping("/getAll")
	public ResponseEntity getAllFraudAlerts() {
		List<FraudAlertResponseDTO> allFraudAlerts = fraudAlertService.getAllFraudAlerts();
		return ResponseEntity.ok(allFraudAlerts);
	}
	
	@GetMapping("getByRiskLevel/{riskLevel}")
	public ResponseEntity getAlertByRiskLevel(@PathVariable RiskLevel riskLevel) {
		List<FraudAlertResponseDTO> alertsByRiskLevel = fraudAlertService.getAlertsByRiskLevel(riskLevel);
		return ResponseEntity.ok(alertsByRiskLevel);
		
	}
	
	@PutMapping("/resolve/{alertId}")
	public ResponseEntity resolveFraudAlert(@PathVariable Long alertId) {
		FraudAlertResponseDTO resolvedAlert = fraudAlertService.resolveFraurdAlert(alertId);
		return ResponseEntity.ok(resolvedAlert);
	
	}
	

	
}
