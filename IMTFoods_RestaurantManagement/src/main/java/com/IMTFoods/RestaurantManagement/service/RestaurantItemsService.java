package com.IMTFoods.RestaurantManagement.service;

import com.IMTFoods.RestaurantManagement.dto.RestaurantItemsResponseDto;

public interface RestaurantItemsService {

	RestaurantItemsResponseDto getItemDetailsById(long foodItemId);

}
