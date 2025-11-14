package com.IMTFoods.RestaurantManagement.dto;

import com.IMTFoods.RestaurantManagement.utils.ItemCategory;
import com.IMTFoods.RestaurantManagement.utils.ItemType;

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
