package com.IMTFoods.UserManagement.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.IMTFoods.UserManagement.dto.RestaurantDetailsResponseDto;

@FeignClient(name = "IMTFoods-RestaurantManagement")
public interface RestaurantClient {
	
	@GetMapping("/restaurant/{id}")
	public ResponseEntity<RestaurantDetailsResponseDto> getRestaurantDetailsById(@PathVariable("id") long restaurantId);
	
	
	
}
