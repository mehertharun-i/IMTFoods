package com.IMTFoods.FoodOrderManagement.service.implementation;

import com.IMTFoods.FoodOrderManagement.builder.FoodOrderResponseDtoBuilder;
import com.IMTFoods.FoodOrderManagement.dao.OrderItemsRepository;
import com.IMTFoods.FoodOrderManagement.dto.OrderItemsResponseDto;
import com.IMTFoods.FoodOrderManagement.exception.OrderItemsIdNotFoundException;
import com.IMTFoods.FoodOrderManagement.model.OrderItems;
import com.IMTFoods.FoodOrderManagement.service.OrderItemsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderItemsServiceImplementation implements OrderItemsService {

	private final OrderItemsRepository orderItemsRepository;
	
	public OrderItemsServiceImplementation(OrderItemsRepository orderItemsRepository) {
		this.orderItemsRepository = orderItemsRepository;
	}

	@Override
	public OrderItemsResponseDto getOrderItemsById(long orderItemsId) {
		
		OrderItems orderItems = orderItemsRepository.findById(orderItemsId).orElseThrow( () -> new OrderItemsIdNotFoundException("Invalid Order Items Id"));
		OrderItemsResponseDto orderItemsResponseDto = FoodOrderResponseDtoBuilder.buildOrderItemsResponseDtoFromOrderItems(orderItems);
		return orderItemsResponseDto;
	}

	@Override
	public List<OrderItemsResponseDto> getAllOrderItems() {
		
		List<OrderItems> allOrderItemsList = orderItemsRepository.findAll();
		
		List<OrderItemsResponseDto> orderItemsResponseDtoList = new ArrayList<>();
		
		for(OrderItems orderItems : allOrderItemsList) {
			OrderItemsResponseDto orderItemsResponseDto = FoodOrderResponseDtoBuilder.buildOrderItemsResponseDtoFromOrderItems(orderItems);
			orderItemsResponseDtoList.add(orderItemsResponseDto);
		}
		
		return orderItemsResponseDtoList;
	}

	@Override
	public void deleteOrderItemsById(long orderItemsId) {
		
		OrderItems orderItems = orderItemsRepository.findById(orderItemsId).orElseThrow( () -> new OrderItemsIdNotFoundException("Invalid Order Items Id"));
		orderItemsRepository.deleteById(orderItems.getOrderItemId());
	}
	
	
	
	
}
