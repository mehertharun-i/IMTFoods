package com.IMTFoods.RestaurantManagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(RestaurantDetailsNotFoundException.class)
	public ResponseEntity<String> handleRestaurantDetailsNotFoundException(RestaurantDetailsNotFoundException exceptionMessage){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionMessage.getMessage());
	}

	@ExceptionHandler(FoodItemIdNotFoundException.class)
	public ResponseEntity<String> handleFoodItemIdNotFoundException(FoodItemIdNotFoundException exceptionMessage){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionMessage.getMessage());
	}
	
	public ResponseEntity<String> handleRestaurantAddressIdNotFoundException(RestaurantAddressIdNotFoundException exceptionMessage){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionMessage.getMessage());
	}
}
