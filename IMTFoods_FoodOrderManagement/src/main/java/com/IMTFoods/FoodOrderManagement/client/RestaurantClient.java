package com.IMTFoods.FoodOrderManagement.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.IMTFoods.FoodOrderManagement.dto.RestaurantDetailsResponseDto;

@FeignClient(name = "IMTFoods-RestaurantManagement")
public interface RestaurantClient {
	

}
