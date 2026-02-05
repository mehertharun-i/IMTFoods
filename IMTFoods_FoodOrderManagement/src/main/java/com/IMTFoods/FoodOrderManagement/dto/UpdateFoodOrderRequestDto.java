package com.IMTFoods.FoodOrderManagement.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateFoodOrderRequestDto {

	private long orderId;
	
	private List<UpdateOrderItemsRequestDto> updatedOrderItemsRequestDto; 
		
	private double updatedOrderTotalPrice;
	
	private UpdatePaymentDetailsRequestDto updatePaymentDetailsRequestDto;
	
}
