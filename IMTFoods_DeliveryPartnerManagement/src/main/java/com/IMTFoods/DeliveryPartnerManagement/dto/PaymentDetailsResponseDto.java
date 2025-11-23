package com.IMTFoods.DeliveryPartnerManagement.dto;

import com.IMTFoods.DeliveryPartnerManagement.utils.PaymentStatus;
import com.IMTFoods.DeliveryPartnerManagement.utils.PaymentType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentDetailsResponseDto {

	private long paymentDetailsResponseDtoPaymentTransactionId;
	
	private double paymentDetailsResponseDtoOrderPaymentActualPrice;
	
	private double paymentDetailsResponseDtoDiscountAmount;
	
	private double paymentDetailsResponseDtoOrderPaymentFinalPrice;
	
	private PaymentType paymentDetailsResponseDtoPaymentType;
	
	private PaymentStatus paymentDetailsResponseDtoPaymentStatus;
	
}
