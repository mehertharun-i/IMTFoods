package com.IMTFoods.RestaurantManagement.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.IMTFoods.RestaurantManagement.dto.RestaurantAddressResponseDto;
import com.IMTFoods.RestaurantManagement.service.RestaurantAddressService;

@RestController
@RequestMapping("/restaurant/address")
public class RestaurantAddressController {
	
	private final RestaurantAddressService restaurantAddressService;
	
	public RestaurantAddressController(RestaurantAddressService restaurantAddressService) {
		this.restaurantAddressService = restaurantAddressService;
	}
	
	
	@GetMapping("/find/{id}")
	public ResponseEntity<RestaurantAddressResponseDto> getRestaurantAddressInformationById(@PathVariable("id") long restaurantAddressId){
		RestaurantAddressResponseDto restaurantAddressResponseDto = restaurantAddressService.getRestaurantAddressInformationById(restaurantAddressId);
		return ResponseEntity.status(HttpStatus.FOUND).body(restaurantAddressResponseDto);
	}

}
