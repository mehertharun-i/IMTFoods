package com.IMTFoods.UserManagement.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.IMTFoods.UserManagement.dto.FavouriteRestaurantResponseDto;
import com.IMTFoods.UserManagement.service.FavouriteRestaurantService;

@RestController
@RequestMapping("/favourite")
public class FavouriteRestaurantController {

	private final FavouriteRestaurantService favouriteRestaurantService;
	
	public FavouriteRestaurantController(FavouriteRestaurantService favouriteRestaurantService) {
		this.favouriteRestaurantService = favouriteRestaurantService;
	}
	
	@PostMapping("/addToFavourite/{userid}/{restaurantid}")
	public ResponseEntity<FavouriteRestaurantResponseDto> addRestaurantToFavouriteList(@PathVariable("userid") long userInformationId, @PathVariable("restaurantid") long restaurantId, @RequestParam(name = "favourite") boolean favouriteStatus){
		if(favouriteStatus == true) {
			FavouriteRestaurantResponseDto RestaurantAddToFavourite = favouriteRestaurantService.addRestaurantToFavouriteList(userInformationId, restaurantId, favouriteStatus);
			return ResponseEntity.status(HttpStatus.CREATED).body(RestaurantAddToFavourite);
		}else {
			favouriteRestaurantService.removeRestaurantFromFavouriteList(userInformationId, restaurantId, favouriteStatus);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}
	
}
