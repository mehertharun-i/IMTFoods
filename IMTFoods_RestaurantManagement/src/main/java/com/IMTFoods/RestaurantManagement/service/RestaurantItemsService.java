package com.IMTFoods.RestaurantManagement.service;

import com.IMTFoods.RestaurantManagement.dto.RestaurantItemsResponseDto;
import com.IMTFoods.RestaurantManagement.utils.ItemsAvailableStatus;

public interface RestaurantItemsService {

	RestaurantItemsResponseDto getItemDetailsById(long foodItemId);

	ItemsAvailableStatus updateFoodItemsAvailablility(long foodItemId, ItemsAvailableStatus availableStatus);

}
