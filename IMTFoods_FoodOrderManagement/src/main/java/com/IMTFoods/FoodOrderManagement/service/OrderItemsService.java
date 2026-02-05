package com.IMTFoods.FoodOrderManagement.service;

import com.IMTFoods.FoodOrderManagement.dto.OrderItemsResponseDto;

import java.util.List;

public interface OrderItemsService {

	OrderItemsResponseDto getOrderItemsById(long orderItemsId);

	List<OrderItemsResponseDto> getAllOrderItems();

	void deleteOrderItemsById(long orderItemsId);

}
