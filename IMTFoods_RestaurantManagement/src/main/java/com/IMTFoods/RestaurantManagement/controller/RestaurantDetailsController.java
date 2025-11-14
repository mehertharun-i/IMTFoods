package com.IMTFoods.RestaurantManagement.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.IMTFoods.RestaurantManagement.dto.RestaurantDetailsRequestDto;
import com.IMTFoods.RestaurantManagement.dto.RestaurantDetailsResponseDto;
import com.IMTFoods.RestaurantManagement.service.RestaurantDetailsService;

@RestController
@RequestMapping("/restaurant")
public class RestaurantDetailsController {
	
	private final RestaurantDetailsService restaurantDetailsService;
	
	public RestaurantDetailsController(RestaurantDetailsService restaurantDetailsService) {
		this.restaurantDetailsService = restaurantDetailsService;
	}
	
	@PostMapping("/register")
	public ResponseEntity<RestaurantDetailsResponseDto> registerRestaurantDetails(@RequestBody RestaurantDetailsRequestDto restaurantDetailsRequestDto){
		ResponseEntity<RestaurantDetailsResponseDto> registeredRestaurant = restaurantDetailsService.registerRestaurantDetails(restaurantDetailsRequestDto);
		return registeredRestaurant;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<RestaurantDetailsResponseDto> getRestaurantDetailsById(@PathVariable("id") long restaurantId){
		ResponseEntity<RestaurantDetailsResponseDto> restaurantDetailsById = restaurantDetailsService.getRestaurantDetailsById(restaurantId);
		return restaurantDetailsById;
	}
	
	@GetMapping
	public ResponseEntity<List<RestaurantDetailsResponseDto>> getAllRestaurantDetails(){
		ResponseEntity<List<RestaurantDetailsResponseDto>> allRestaurantDetails = restaurantDetailsService.getAllRestaurantDetails();
		return allRestaurantDetails;
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteRestaurantById(@PathVariable("id") long restaurantId){
		ResponseEntity<Void> deleteRestaurantById = restaurantDetailsService.deleteRestaurantById(restaurantId);
		return deleteRestaurantById;
	}
	
	@PutMapping("update/{id}")
	public ResponseEntity<RestaurantDetailsResponseDto> updateRestaurantDetailsById(@PathVariable("id") long restaurantId, @RequestBody RestaurantDetailsRequestDto restaurantDetailsRequestDto){
		ResponseEntity<RestaurantDetailsResponseDto> updatedRestaurantDetails = restaurantDetailsService.updateRestaurantDetailsById(restaurantId, restaurantDetailsRequestDto);
		return updatedRestaurantDetails;
	}

}



























