package com.IMTFoods.FoodOrderManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FoodOrderRequestDto {
	
	private long foodOrderRequestDtoUserId;
	
	private long foodOrderRequestDtoUserAddressId;
	
	private long foodOrderRequestDtoRestaurantId;
	
	private long foodOrderRequestDtoRestaurantAddressId;
	
	private List<OrderItemsRequestDto> foodOrderRequestDtoOrderItems;
	
	private double foodOrderRequestDtoOrderTotalPrice;
	
	private PaymentDetailsRequestDto foodOrderRequestDtoPaymentDetails;

}
