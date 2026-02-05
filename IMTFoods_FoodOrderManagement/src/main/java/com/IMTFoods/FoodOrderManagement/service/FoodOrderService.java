package com.IMTFoods.FoodOrderManagement.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.IMTFoods.FoodOrderManagement.dto.FoodOrderRequestDto;
import com.IMTFoods.FoodOrderManagement.dto.FoodOrderResponseDto;
import com.IMTFoods.FoodOrderManagement.dto.UpdateFoodOrderRequestDto;

public interface FoodOrderService {

	FoodOrderResponseDto orderFood(FoodOrderRequestDto foodOrderRequestDto) throws Exception;

	FoodOrderResponseDto getOrderedFoodDetailsById(long orderId);

	List<FoodOrderResponseDto> getAllOrderedFoodDetails();

	void deleteOrderedFood(long orderedFoodId);

	List<FoodOrderResponseDto> orderListOfFoods(List<FoodOrderRequestDto> foodOrderRequestDtoList) throws Exception;

	Page<FoodOrderResponseDto> orderedHistory(long userId, int pageNumber, int pageSize);

	FoodOrderResponseDto reOrderFood(long orderedFoodId) throws Exception;

	FoodOrderResponseDto updateOrderFood(UpdateFoodOrderRequestDto updateFoodOrderRequestDto, long userId);
		

}
