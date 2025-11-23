package com.IMTFoods.FoodOrderManagement.service;

import java.util.List;

import com.IMTFoods.FoodOrderManagement.dto.FoodOrderRequestDto;
import com.IMTFoods.FoodOrderManagement.dto.FoodOrderResponseDto;
import com.IMTFoods.FoodOrderManagement.exception.UserAddressAndRestaurantAddressAreNotCloserException;

public interface FoodOrderService {

	FoodOrderResponseDto orderFood(FoodOrderRequestDto foodOrderRequestDto) throws UserAddressAndRestaurantAddressAreNotCloserException;

	FoodOrderResponseDto getOrderedFoodDetailsById(long orderId);

	List<FoodOrderResponseDto> getAllOrderedFoodDetails();

}
