package com.IMTFoods.FoodOrderManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemsRequestDto {
	
	private long orderItemsRequestDtoRestaurantFoodItemsId;
		
	private double orderItemsRequestDtoFoodItemPrice;
	
	private int orderItemsRequestDtoQuantity;

}
