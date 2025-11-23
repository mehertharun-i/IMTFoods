package com.IMTFoods.DeliveryPartnerManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantAddressResponseDto {
	
	private String restaurantHouseNumberResponseDto;
	
	private String restaurantStreetResponseDto;
	
	private String restaurantLandMarkResponseDto;
	
	private String restaurantDistrictResponseDto;
	
	private String restaurantStateResponseDto;
	
	private String restaurantCountryResponseDto;
	
	private int restaurantPincodeResponseDto;

}
