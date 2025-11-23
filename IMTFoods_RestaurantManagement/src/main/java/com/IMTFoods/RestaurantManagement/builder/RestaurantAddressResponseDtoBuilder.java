package com.IMTFoods.RestaurantManagement.builder;

import com.IMTFoods.RestaurantManagement.dto.RestaurantAddressResponseDto;
import com.IMTFoods.RestaurantManagement.model.RestaurantAddress;

public class RestaurantAddressResponseDtoBuilder {
	
	public static RestaurantAddressResponseDto buildRestaurantAddressResponseDtoFromRestaurantAddress(RestaurantAddress restaurantAddress) {
		
		RestaurantAddressResponseDto restaurantAddressResponseDto = RestaurantAddressResponseDto.builder()
										.restaurantHouseNumberResponseDto(restaurantAddress.getRestaurantHouseNumber())
										.restaurantLandMarkResponseDto(restaurantAddress.getRestaurantLandMark())
										.restaurantStreetResponseDto(restaurantAddress.getRestaurantStreet())
										.restaurantDistrictResponseDto(restaurantAddress.getRestaurantDistrict())
										.restaurantStateResponseDto(restaurantAddress.getRestaurantState())
										.restaurantCountryResponseDto(restaurantAddress.getRestaurantCountry())
										.restaurantPincodeResponseDto(restaurantAddress.getRestaurantPincode())
										.build();
		return restaurantAddressResponseDto;
	}

}
