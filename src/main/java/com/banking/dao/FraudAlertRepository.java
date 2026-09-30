package com.banking.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.model.FraudAlert;

public interface FraudAlertRepository extends JpaRepository<FraudAlert,Long>{

}
