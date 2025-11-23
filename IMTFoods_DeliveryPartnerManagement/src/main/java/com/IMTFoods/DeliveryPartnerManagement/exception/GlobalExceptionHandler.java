package com.IMTFoods.DeliveryPartnerManagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(DeliveryPartnerIdNotFoundException.class)
	public ResponseEntity<String> handleDeliveryPartnerIdNotFoundException(DeliveryPartnerIdNotFoundException exceptionMessage){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionMessage.getMessage());
	}
	
	@ExceptionHandler(DeliveryAssignmentIdNotFoundException.class)
	public ResponseEntity<String> handleDeliveryAssignmentIdNotFoundException(DeliveryAssignmentIdNotFoundException exceptionMessage){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionMessage.getMessage());
	}
	
}
