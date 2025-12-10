package com.IMTFoods.FoodOrderManagement.service;

import java.util.List;

import com.IMTFoods.FoodOrderManagement.dto.OrderItemsResponseDto;

public interface OrderItemsService {

	OrderItemsResponseDto getOrderItemsById(long orderItemsId);

	List<OrderItemsResponseDto> getAllOrderItems();

	void deleteOrderItemsById(long orderItemsId);

}
