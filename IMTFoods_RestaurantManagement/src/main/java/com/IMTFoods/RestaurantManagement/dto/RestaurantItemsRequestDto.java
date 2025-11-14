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
public class RestaurantItemsRequestDto {
	
	private String itemNameRequestDto;
	
	private int itemPriceRequestDto;
	
	private ItemCategory itemCategoryRequestDto;
	
	private double itemRatingRequestDto;
	
	private String itemDescriptionRequestDto;
	
	private ItemType itemTypeRequestDto;
		
}
