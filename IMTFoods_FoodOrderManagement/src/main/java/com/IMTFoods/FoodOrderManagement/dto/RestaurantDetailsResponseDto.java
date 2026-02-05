package com.IMTFoods.FoodOrderManagement.dto;

import com.IMTFoods.FoodOrderManagement.utils.RestaurantType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantDetailsResponseDto {
	
	private String restaurantNameResponseDto;
	
	private String restaurantOwnerNameResponseDto;
	
	private String restaurantPhoneNumberResponseDto;
	
	private RestaurantType restaurantTypeResponseDto;
	
	private double restaurantRatingResponseDto;
	
	private boolean isRestaurantOpenedResponeDto;
	
	private List<RestaurantItemsResponseDto> restaurantItemResponseDto;
	
	private List<RestaurantAddressResponseDto> restaurantAddressResponseDto;

}
