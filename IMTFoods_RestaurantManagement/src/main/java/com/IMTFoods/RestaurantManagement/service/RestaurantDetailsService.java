package com.IMTFoods.RestaurantManagement.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.IMTFoods.RestaurantManagement.dto.RestaurantDetailsRequestDto;
import com.IMTFoods.RestaurantManagement.dto.RestaurantDetailsResponseDto;

public interface RestaurantDetailsService {

	ResponseEntity<RestaurantDetailsResponseDto> registerRestaurantDetails(RestaurantDetailsRequestDto restaurantDetailsRequestDto);

	ResponseEntity<RestaurantDetailsResponseDto> getRestaurantDetailsById(long restaurantId);

	ResponseEntity<List<RestaurantDetailsResponseDto>> getAllRestaurantDetails();

	ResponseEntity<Void> deleteRestaurantById(long restaurantId);

	ResponseEntity<RestaurantDetailsResponseDto> updateRestaurantDetailsById(long restaurantId, RestaurantDetailsRequestDto restaurantDetailsRequestDto);
	
	
}
