package com.IMTFoods.FoodOrderManagement.service;

import com.IMTFoods.FoodOrderManagement.dto.FoodOrderRequestDto;
import com.IMTFoods.FoodOrderManagement.dto.FoodOrderResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

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
