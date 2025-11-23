package com.IMTFoods.FoodOrderManagement.dto;

import com.IMTFoods.FoodOrderManagement.utils.PaymentType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDetailsRequestDto {

	private double paymentDetailsRequestDtoOrderPaymentActualPrice;
	
	private double paymentDetailsRequestDtoDiscountAmount;
	
	private double paymentDetailsRequestDtoOrderPaymentFinalPrice;
	
	private PaymentType paymentDetailsRequestDtoPaymentType;
	
}
