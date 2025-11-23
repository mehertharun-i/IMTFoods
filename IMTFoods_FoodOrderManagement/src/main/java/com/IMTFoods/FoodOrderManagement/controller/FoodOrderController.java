package com.IMTFoods.FoodOrderManagement.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.IMTFoods.FoodOrderManagement.dto.FoodOrderRequestDto;
import com.IMTFoods.FoodOrderManagement.dto.FoodOrderResponseDto;
import com.IMTFoods.FoodOrderManagement.exception.UserAddressAndRestaurantAddressAreNotCloserException;
import com.IMTFoods.FoodOrderManagement.service.FoodOrderService;

@RestController
@RequestMapping("/order")
public class FoodOrderController {

	private final FoodOrderService foodOrderService;
	
	public FoodOrderController(FoodOrderService foodOrderService) {
		this.foodOrderService = foodOrderService;
	}
	
	@PostMapping("/foodorder")
	public ResponseEntity<FoodOrderResponseDto> orderFood(@RequestBody FoodOrderRequestDto foodOrderRequestDto) throws UserAddressAndRestaurantAddressAreNotCloserException{
		FoodOrderResponseDto orderFood = foodOrderService.orderFood(foodOrderRequestDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(orderFood);
	}
	
	@GetMapping("/find/{id}")
	public ResponseEntity<FoodOrderResponseDto> getOrderedFoodDetailsById(@PathVariable("id") long orderId){
		FoodOrderResponseDto foodOrderResponseDto = foodOrderService.getOrderedFoodDetailsById(orderId);
		return ResponseEntity.status(HttpStatus.FOUND).body(foodOrderResponseDto);
	}
	
	@GetMapping("/find/all")
	public ResponseEntity<List<FoodOrderResponseDto>> getAllOrderedFoodDetails(){
		List<FoodOrderResponseDto> foodOrderResponseDto = foodOrderService.getAllOrderedFoodDetails();
		return ResponseEntity.status(HttpStatus.FOUND).body(foodOrderResponseDto);
	}
	
}
