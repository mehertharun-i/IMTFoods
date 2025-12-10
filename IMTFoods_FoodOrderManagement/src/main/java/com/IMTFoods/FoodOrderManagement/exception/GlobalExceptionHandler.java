package com.IMTFoods.FoodOrderManagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(UserAddressAndRestaurantAddressAreNotCloserException.class)
	public ResponseEntity<String> handleUserAddressAndRestaurantAddressAreNotCloserException(UserAddressAndRestaurantAddressAreNotCloserException exceptionMessage){
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(exceptionMessage.getMessage());
	}
	
	@ExceptionHandler(OrderedFoodIdNotFoundException.class)
	public ResponseEntity<String> handleOrderedFoodIdNotFoundException(OrderedFoodIdNotFoundException exceptionMessage){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionMessage.getMessage());
	}
	
	@ExceptionHandler(OrderItemsIdNotFoundException.class)
	public ResponseEntity<String> handleOrderItemsIdNotFoundException(OrderItemsIdNotFoundException exceptionMessage){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionMessage.getMessage());
	}
	
	@ExceptionHandler(PaymentDetailsIdNotFoundException.class)
	public ResponseEntity<String> handlePaymentDetailsIdNotFoundException(PaymentDetailsIdNotFoundException exceptionMessage){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionMessage.getMessage());
	}

}
