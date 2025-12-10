package com.IMTFoods.RestaurantManagement.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.IMTFoods.RestaurantManagement.dto.RestaurantItemsResponseDto;
import com.IMTFoods.RestaurantManagement.service.RestaurantItemsService;
import com.IMTFoods.RestaurantManagement.utils.ItemsAvailableStatus;

@RestController
@RequestMapping("/restaurantitems")
public class RestaurantItemsController {

	private final RestaurantItemsService restaurantItemsService;
	
	public RestaurantItemsController(RestaurantItemsService restaurantItemsService) {
		this.restaurantItemsService = restaurantItemsService;
	}
	
	
	@GetMapping("/fooditem/{id}")
	public ResponseEntity<RestaurantItemsResponseDto> getItemDetailsById(@PathVariable("id") long foodItemId){
		RestaurantItemsResponseDto foodItemDetailsById = restaurantItemsService.getItemDetailsById(foodItemId);
		return ResponseEntity.status(HttpStatus.FOUND).body(foodItemDetailsById);
	}
	
	@GetMapping("/fooditem/name/{id}")
	public ResponseEntity<String> getItemNameById(@PathVariable("id") long foodItemId){
		RestaurantItemsResponseDto foodItemNameById = restaurantItemsService.getItemDetailsById(foodItemId);
		return ResponseEntity.status(HttpStatus.FOUND).body(foodItemNameById.getItemNameResponseDto());
	}
	
	@PutMapping("/fooditem/update/{id}")
	public ResponseEntity<ItemsAvailableStatus> updateFoodItemsAvailablility(@PathVariable("id") long foodItemId, @RequestParam("status") ItemsAvailableStatus availableStatus){
		ItemsAvailableStatus itemsAvailableStatus = restaurantItemsService.updateFoodItemsAvailablility(foodItemId, availableStatus);
		return ResponseEntity.status(HttpStatus.OK).body(itemsAvailableStatus);
	}
	
}
