package com.banking.aspect;

import com.banking.controller.AccountDetailsController;
import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.banking.dto.TransactionResponseDTO;


@Aspect
@Component
public class LoggingAspect {
	
	private final AccountDetailsController accountDetailsController;
	private static final Logger Log = LoggerFactory.getLogger(LoggingAspect.class);

	LoggingAspect(AccountDetailsController accountDetailsController) {
		this.accountDetailsController = accountDetailsController;
	}
	    
	 @EventListener(ApplicationReadyEvent.class)
	public void onApplicationReady() {
		Log.info("Smart Bank Managment Application Started sucessfully and ready to take the requests.");
	}
	 
	 @Before("execution(*com.banking.controller.*.*(..)")
	 public void requestRecived(JoinPoint joinPoint) {
		 Log.info("Request Recived : {}", joinPoint.getSignature().getName());
	 }
	 
	@Before("execution(* com.banking.controller.*.*(..)")
	public void logBefore(JoinPoint joinPoint) {
		Log.info("Entered Into the method: {}", joinPoint.getSignature().getName());
	}
	
	@AfterReturning("execution(* com.banking.controller.*.*(..)")
	public void logAfterMethodCompleted(JoinPoint joinPoint) {
		Log.info("Method Completed Sucessfully : {}", joinPoint.getSignature().getName());
	}
	
	@AfterReturning("excecution(* com.banking.controller.*.*(..)")
	public void logRecoredSavedSucessfully(JoinPoint joinPoint) {
		Log.info("Recored Saved Sucessfully : {}", joinPoint.getSignature().getName());
	}
	
	@After("execution(*com.banking.controller.*.*(..)")
	public void logAfter(JoinPoint joinPoint) {
		Log.info("Rerurning From method : {}", joinPoint.getSignature().getName());
	}
	
	@AfterReturning("exeception(* com.banking.controller.*.*(..)")
	public void logAfterUpdated(JoinPoint joinPoint) {
		Log.info("BankingDetails Updated Sucessfully");
	}
	
	@After("exeception(* com.banking.controller.*.*(..)")
	public void logExcecutionTimeCompleted(JoinPoint executionTime) {
		Log.info("ExcecutionCompleted in {} ms ", executionTime);
	}
	
//	Debug Level Logs
	
	@Before("execution(* com.banking.controller.*.*(..)")
	public void logMethodArguments(JoinPoint joinPoint) {
		Log.debug("Method Arguments:{}", Arrays.toString(joinPoint.getArgs()) );
	}
	
	@AfterReturning("execution(* com.banking.controller.*.*(..)")
	public void logFetchingDataFromDataBase(JoinPoint joinpoint) {
		Log.debug("Fetching the detais from the database");
	}
	
	@Before("execution(* com.banking.controller.*.*(..)")
	public void logValidatingRequest(JoinPoint joinpoint) {
		Log.debug("Validating the request:{}", joinpoint.getSignature().getName() );
	}
	
	@Before("execution(* com.banking.controller.*.*(..)")
	public void logSavingTheData(JoinPoint joinPoint) {
		Log.debug("Saving the data into the database");
	}
	
	@AfterReturning(
			pointcut = "execution(* com.banking.controller.*.*(..)",
			returning = "responseList"
			)
	public void logAfterDataRetrived(JoinPoint joinPoint, List<TransactionResponseDTO> responseList) {
		Log.debug("Retrived Transactions from database{}", responseList.size());
	}
	
	@After("execution(* com.banking.controller.*.*(..)")
	public void logAfterCompletion(JoinPoint joinPoint) {
		Log.debug("DataBase Operation Completed  Sucessfully");
	}
	
//	Warn Level
	
	@AfterThrowing("execution(* com.banking.controller.*.*(..)")
	public void logDataNotFound(JoinPoint joinPoint) {
		Log.warn("No Data found for the request ", joinPoint.getSignature().getName());
	}
	
	@AfterThrowing("execution(* com.banking.controller.*.*(..)")
	public void logInvalifInput(JoinPoint joinPoint) {
		Log.warn("Invalid Input Recived");
	}
	
	@Before("execution(* com.banking.controller.*.*(..)")
	public void logDepricatedAPIUsage(JoinPoint joinPoint) {
		Log.warn("Deprcated Endpoint Invoked");
	}
	
//	Error Level
	
	@AfterThrowing(pointcut = "execution(* com.banking.serviceimpl.*.*(..)",
			throwing = "exception"
			)
	public void handleException(Exception exception) {
		Log.error("Encountered Exception : {}", exception.getMessage());
	}
	
	@AfterThrowing(
			pointcut = "execution(* com.banking.serviceimpl.*.*(..)",
			throwing = "exception"
			)
	public void handleDataBaseException(Exception exception) {
		Log.error("Encountered Exception : {}", exception.getMessage());
	}
}

