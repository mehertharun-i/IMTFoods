package com.IMTFoods.RestaurantManagement.service;

import java.util.List;

import com.IMTFoods.RestaurantManagement.dto.RestaurantDetailsRequestDto;
import com.IMTFoods.RestaurantManagement.dto.RestaurantDetailsResponseDto;
import com.IMTFoods.RestaurantManagement.utils.RestaurantStatus;

public interface RestaurantDetailsService {

	RestaurantDetailsResponseDto registerRestaurantDetails(RestaurantDetailsRequestDto restaurantDetailsRequestDto);

	RestaurantDetailsResponseDto getRestaurantDetailsById(long restaurantId);

	List<RestaurantDetailsResponseDto> getAllRestaurantDetails();

	void deleteRestaurantById(long restaurantId);

	RestaurantDetailsResponseDto updateRestaurantDetailsById(long restaurantId, RestaurantDetailsRequestDto restaurantDetailsRequestDto);

	String updateRestaurantStatusById(long restaurantDetailsId, RestaurantStatus restaurantStatus);
	
	
}
