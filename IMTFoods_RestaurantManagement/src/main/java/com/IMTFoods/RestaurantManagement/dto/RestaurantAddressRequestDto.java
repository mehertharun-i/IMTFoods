package com.IMTFoods.RestaurantManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantAddressRequestDto {

	private String restaurantHouseNumberRequestDto;
	
	private String restaurantStreetRequestDto;
	
	private String restaurantLandMarkRequestDto;
	
	private String restaurantDistrictRequestDto;
	
	private String restaurantStateRequestDto;
	
	private String restaurantCountryRequestDto;
	
	private int restaurantPincodeRequestDto;
		
}
