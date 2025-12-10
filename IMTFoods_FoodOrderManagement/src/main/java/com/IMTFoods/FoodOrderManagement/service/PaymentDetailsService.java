package com.IMTFoods.FoodOrderManagement.service;

import java.util.List;

import com.IMTFoods.FoodOrderManagement.dto.PaymentDetailsResponseDto;

public interface PaymentDetailsService {

	PaymentDetailsResponseDto getPaymentDetailsById(long paymentDetailsId);

	List<PaymentDetailsResponseDto> getAllPaymentDetails();

	void deletePaymentDetailsById(long paymentDetailsId);

	
	
}
