package com.IMTFoods.RestaurantManagement.builder;

import java.util.ArrayList;
import java.util.List;

import com.IMTFoods.RestaurantManagement.dto.RestaurantDetailsResponseDto;
import com.IMTFoods.RestaurantManagement.model.RestaurantDetails;

public class ListOfRestaurantDetailsResponseBuilder {

	public static List<RestaurantDetailsResponseDto> buildListOfRestaurantDetailsResponseBuilder(List<RestaurantDetails> allRestaurantDetailsList) {

		List<RestaurantDetailsResponseDto> restaurantDetailsResponseDtoList = new ArrayList<>();
		
		for(RestaurantDetails restaurantDetails : allRestaurantDetailsList) {
			RestaurantDetailsResponseDto restaurantDetailsResponseDto = RestaurantDetailsResponseBuilder.buildRestaurantDetailsFromRestaurantDetailsResponseDto(restaurantDetails);
			restaurantDetailsResponseDtoList.add(restaurantDetailsResponseDto);
		}
		
		return restaurantDetailsResponseDtoList;
	}
	

}
