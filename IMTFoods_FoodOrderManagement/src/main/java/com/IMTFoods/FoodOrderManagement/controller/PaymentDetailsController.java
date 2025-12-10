package com.IMTFoods.FoodOrderManagement.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.IMTFoods.FoodOrderManagement.dto.PaymentDetailsResponseDto;
import com.IMTFoods.FoodOrderManagement.service.PaymentDetailsService;

@RestController
@RequestMapping("/paymentdetails")
public class PaymentDetailsController {

	private final PaymentDetailsService paymentDetailsService;
	
	public PaymentDetailsController(PaymentDetailsService paymentDetailsService) {
		this.paymentDetailsService = paymentDetailsService;
	}
	
	@GetMapping("/find/{id}")
	public ResponseEntity<PaymentDetailsResponseDto> getPaymentDetailsById(@PathVariable("id") long paymentDetailsId){
		PaymentDetailsResponseDto paymentDetailsResponseDto = paymentDetailsService.getPaymentDetailsById(paymentDetailsId);
		return ResponseEntity.status(HttpStatus.FOUND).body(paymentDetailsResponseDto);
	}
	
	@GetMapping("/find/all")
	public ResponseEntity<List<PaymentDetailsResponseDto>> getAllPayementDetails(){	
		List<PaymentDetailsResponseDto> allPaymentDetails = paymentDetailsService.getAllPaymentDetails();
		return ResponseEntity.status(HttpStatus.FOUND).body(allPaymentDetails);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deletePaymentDetailsById(@PathVariable("id") long paymentDetailsId){
		paymentDetailsService.deletePaymentDetailsById(paymentDetailsId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}

