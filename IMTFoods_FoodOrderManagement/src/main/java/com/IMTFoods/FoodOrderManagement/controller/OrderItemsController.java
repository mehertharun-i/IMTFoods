package com.IMTFoods.FoodOrderManagement.controller;

import com.IMTFoods.FoodOrderManagement.dto.OrderItemsResponseDto;
import com.IMTFoods.FoodOrderManagement.service.OrderItemsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orderitems")
public class OrderItemsController {

	private final OrderItemsService orderItemsService;
	
	public OrderItemsController(OrderItemsService orderItemsService) {
		this.orderItemsService = orderItemsService;
	}
	
	@GetMapping("/find/{id}")
	public ResponseEntity<OrderItemsResponseDto> getOrderItemsById( @PathVariable("id") long orderItemsId){
		OrderItemsResponseDto orderItemsById = orderItemsService.getOrderItemsById(orderItemsId);
		return ResponseEntity.status(HttpStatus.FOUND).body(orderItemsById);
	}
	
	@GetMapping("/find/all")
	public ResponseEntity<List<OrderItemsResponseDto>> getAllOrderItems(){
		List<OrderItemsResponseDto> allOrderItems = orderItemsService.getAllOrderItems();
		return ResponseEntity.status(HttpStatus.FOUND).body(allOrderItems);
	}
	
	@GetMapping("/delete/{id}")
	public ResponseEntity<Void> deleteOrderItemsById(@PathVariable("id") long orderItemsId){
		
		orderItemsService.deleteOrderItemsById(orderItemsId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
