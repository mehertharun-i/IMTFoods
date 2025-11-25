package com.IMTFoods.FoodOrderManagement.service;

import java.util.List;

import com.IMTFoods.FoodOrderManagement.dto.FoodOrderRequestDto;
import com.IMTFoods.FoodOrderManagement.dto.FoodOrderResponseDto;

public interface FoodOrderService {

	FoodOrderResponseDto orderFood(FoodOrderRequestDto foodOrderRequestDto) throws Exception;

	FoodOrderResponseDto getOrderedFoodDetailsById(long orderId);

	List<FoodOrderResponseDto> getAllOrderedFoodDetails();

	void deleteOrderedFood(long orderedFoodId);

	List<FoodOrderResponseDto> orderListOfFoods(List<FoodOrderRequestDto> foodOrderRequestDtoList) throws Exception;
	

}
