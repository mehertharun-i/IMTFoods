package com.IMTFoods.FoodOrderManagement.service;

import com.IMTFoods.FoodOrderManagement.dto.PaymentDetailsResponseDto;

import java.util.List;

public interface PaymentDetailsService {

	PaymentDetailsResponseDto getPaymentDetailsById(long paymentDetailsId);

	List<PaymentDetailsResponseDto> getAllPaymentDetails();

	void deletePaymentDetailsById(long paymentDetailsId);

	
	
}
