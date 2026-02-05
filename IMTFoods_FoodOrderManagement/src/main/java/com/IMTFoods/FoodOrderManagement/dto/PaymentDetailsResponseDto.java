package com.IMTFoods.FoodOrderManagement.dto;

import com.IMTFoods.FoodOrderManagement.utils.PaymentStatus;
import com.IMTFoods.FoodOrderManagement.utils.PaymentType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentDetailsResponseDto {
	
	private long paymentDetailsResponseDtoPaymentDetailsId;

	private long paymentDetailsResponseDtoPaymentTransactionId;
	
	private double paymentDetailsResponseDtoOrderPaymentActualPrice;
	
	private double paymentDetailsResponseDtoDiscountAmount;
	
	private double paymentDetailsResponseDtoOrderPaymentFinalPrice;
	
	private PaymentType paymentDetailsResponseDtoPaymentType;
	
	private PaymentStatus paymentDetailsResponseDtoPaymentStatus;
	
}
