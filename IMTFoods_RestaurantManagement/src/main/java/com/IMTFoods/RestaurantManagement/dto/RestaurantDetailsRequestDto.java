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
public class RestaurantDetailsRequestDto {

	private String restaurantNameRequestDto;
	
	private String restaurantOwnerNameRequestDto;
	
	private RestaurantType restaurantTypeRequestDto;
	
	private double restaurantRatingRequestDto;
	
	private List<RestaurantItemsRequestDto> restaurantItemsRequestDto;
	
	private List<RestaurantAddressRequestDto> restaurantAddressRequestDto;
	
	
}
