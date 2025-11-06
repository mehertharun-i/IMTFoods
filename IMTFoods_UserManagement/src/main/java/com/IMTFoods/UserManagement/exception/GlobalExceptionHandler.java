package com.IMTFoods.UserManagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UserIdNotFoundException.class)
	public ResponseEntity<String> handleUserIdNotFoundException(UserIdNotFoundException exceptionMessage){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionMessage.getMessage());
	}
	
	@ExceptionHandler(NoContentFoundException.class)
	@ResponseBody
	public ResponseEntity<String> handleNoContentFoundException(NoContentFoundException exceptionMessage){
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(exceptionMessage.getMessage());
	}
}
