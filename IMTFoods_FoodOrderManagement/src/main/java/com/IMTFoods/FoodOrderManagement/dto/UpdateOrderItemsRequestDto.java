package com.IMTFoods.FoodOrderManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateOrderItemsRequestDto {
	
	private long orderItemId;

	private long updateOrderItemRequestDtoRestaurantFoodItemsId;
	
	private double updateOrderItemsRequestDtoFoodItemPrice;
	
	private int updateOrderItemsRequestDtoQuantity;
	
}
