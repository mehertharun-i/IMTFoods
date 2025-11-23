package com.IMTFoods.DeliveryPartnerManagement.dto;

import java.util.List;

import com.IMTFoods.DeliveryPartnerManagement.utils.RestaurantType;

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
	
	private String restaurantPhoneNumberResponseDto;
	
	private RestaurantType restaurantTypeResponseDto;
	
	private double restaurantRatingResponseDto;
	
	private boolean isRestaurantOpenedResponeDto;
	
	private List<RestaurantItemsResponseDto> restaurantItemResponseDto;
	
	private List<RestaurantAddressResponseDto> restaurantAddressResponseDto;

}
