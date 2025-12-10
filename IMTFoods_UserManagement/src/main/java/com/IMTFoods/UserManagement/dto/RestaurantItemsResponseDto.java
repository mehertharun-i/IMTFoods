package com.IMTFoods.UserManagement.dto;

import com.IMTFoods.UserManagement.utils.ItemCategory;
import com.IMTFoods.UserManagement.utils.ItemType;

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
