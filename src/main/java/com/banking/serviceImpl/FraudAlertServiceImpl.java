package com.banking.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.dao.FraudAlertRepository;
import com.banking.dto.FraudAlertRequestDTO;
import com.banking.dto.FraudAlertResponseDTO;
import com.banking.enums.RiskLevel;
import com.banking.exception.FraudAlertNotFoundException;
import com.banking.model.FraudAlert;
import com.banking.service.FraudAlertService;

@Service
public class FraudAlertServiceImpl implements FraudAlertService{
	
	@Autowired
	FraudAlertRepository fraudAlertRepository;

	@Override
public FraudAlertResponseDTO createFraudAlert(FraudAlertRequestDTO alertRequestDTO) {
    // 1. Create a new entity instance
    FraudAlert fraudAlert = new FraudAlert();
    
    // 2. Copy properties from DTO to Entity
    BeanUtils.copyProperties(alertRequestDTO, fraudAlert);
    
    // 3. Save the entity to the database
    FraudAlert savedAlert = fraudAlertRepository.save(fraudAlert);
    
    // 4. Create a response DTO and copy properties from the saved entity
    FraudAlertResponseDTO responseDTO = new FraudAlertResponseDTO();
    BeanUtils.copyProperties(savedAlert, responseDTO);
    
    return responseDTO;
}


	@Override
	public FraudAlertResponseDTO getFraudAlertById(Long alertId) {
		Optional<FraudAlert> byId = fraudAlertRepository.findById(alertId);
		FraudAlert alert = byId.get();
		if(!byId.isPresent()) {
			throw new FraudAlertNotFoundException("Fraud Alert is Not Found With the Provided alertId : " + alertId);
		}
		
		
		FraudAlertResponseDTO alertResponseDTO = new FraudAlertResponseDTO();
		BeanUtils.copyProperties(alert, alertResponseDTO);
		return alertResponseDTO;
	}

	@Override
	public List<FraudAlertResponseDTO> getAllFraudAlerts() {
		List<FraudAlert> allAlerts = fraudAlertRepository.findAll();
		ArrayList<FraudAlertResponseDTO> arrayList = new ArrayList<FraudAlertResponseDTO>();
		
		for(FraudAlert alert : allAlerts) {
			FraudAlertResponseDTO alertResponseDTO = new FraudAlertResponseDTO();
			BeanUtils.copyProperties(alert, alertResponseDTO);
			arrayList.add(alertResponseDTO);
		}
		return arrayList;
	}

	@Override
	public List<FraudAlertResponseDTO> getAlertsByRiskLevel(RiskLevel riskLevel) {
		List<FraudAlert> byRiskLevel = fraudAlertRepository.findByRiskLevel(riskLevel);
		ArrayList<FraudAlertResponseDTO> responseList = new ArrayList<FraudAlertResponseDTO>();
		for(FraudAlert fraudAlert : byRiskLevel) {
			
			FraudAlertResponseDTO alertResponseDTO = new FraudAlertResponseDTO();
			BeanUtils.copyProperties(fraudAlert, alertResponseDTO);
			responseList.add(alertResponseDTO);
		}
		return responseList;
	}

	
	
public FraudAlertResponseDTO resolveFraurdAlert(Long alertId) {
    // 1. Find the alert by ID
    Optional<FraudAlert> optionalAlert = fraudAlertRepository.findById(alertId);
    
    if (optionalAlert.isPresent()) {
        FraudAlert alert = optionalAlert.get();
        
        // 2. Update the status (assuming a setStatus method exists in your model)
        alert.setStatus("RESOLVED"); 
        
        // 3. Save the updated alert
        FraudAlert updatedAlert = fraudAlertRepository.save(alert);
        
        // 4. Convert to DTO and return
        FraudAlertResponseDTO responseDTO = new FraudAlertResponseDTO();
        BeanUtils.copyProperties(updatedAlert, responseDTO);
        return responseDTO;
    }else {
    	throw new FraudAlertNotFoundException("Fraud Alert is not found with the provided Id :" + alertId);
    }
    
    
}

 
	}

