package com.IMTFoods.RestaurantManagement.service;

import com.IMTFoods.RestaurantManagement.dto.RestaurantAddressResponseDto;

public interface RestaurantAddressService {

	RestaurantAddressResponseDto getRestaurantAddressInformationById(long restaurantAddressId);

}
