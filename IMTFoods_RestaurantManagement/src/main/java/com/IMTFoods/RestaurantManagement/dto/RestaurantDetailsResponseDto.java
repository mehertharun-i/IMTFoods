package com.IMTFoods.RestaurantManagement.dto;

import java.util.List;

import com.IMTFoods.RestaurantManagement.utils.RestaurantType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantDetailsResponseDto {
	
	private String restaurantNameResponseDto;
	
	private String restaurantOwnerNameResponseDto;
	
	private RestaurantType restaurantTypeResponseDto;
	
	private double restaurantRatingResponseDto;
	
	private boolean isRestaurantOpenedResponeDto;
	
	private List<RestaurantItemsResponseDto> restaurantItemResponseDto;
	
	private List<RestaurantAddressResponseDto> restaurantAddressResponseDto;

}
