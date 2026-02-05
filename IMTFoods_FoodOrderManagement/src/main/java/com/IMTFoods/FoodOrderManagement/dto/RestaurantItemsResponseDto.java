package com.IMTFoods.FoodOrderManagement.dto;

import com.IMTFoods.FoodOrderManagement.utils.ItemCategory;
import com.IMTFoods.FoodOrderManagement.utils.ItemType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantItemsResponseDto {
	
	private String itemNameResponseDto;
	
	private int itemPriceResponseDto;
	
	private ItemCategory itemCategoryResponseDto;
	
	private double itemRatingResponseDto;
	
	private String itemDescriptionResponseDto;
	
	private ItemType itemTypeResponseDto;
	
}
