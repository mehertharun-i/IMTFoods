package com.IMTFoods.UserManagement.builder;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;

import com.IMTFoods.UserManagement.client.RestaurantClient;
import com.IMTFoods.UserManagement.dto.FavouriteRestaurantResponseDto;
import com.IMTFoods.UserManagement.dto.RestaurantDetailsResponseDto;
import com.IMTFoods.UserManagement.model.FavouriteRestaurant;

@Configuration
public class FavouriteRestaurantResponseBuilder {
	
	private final RestaurantClient restaurantClient;
	
	public FavouriteRestaurantResponseBuilder(RestaurantClient restaurantClient) {
		this.restaurantClient = restaurantClient;
	}

	
	public FavouriteRestaurantResponseDto buildFavouriteRestaurantResponseDtoFormFavouriteRestaurant(FavouriteRestaurant favouriteRestaurant) {
		
		FavouriteRestaurantResponseDto favouriteRestaurantResponseDto = FavouriteRestaurantResponseDto.builder()
																									.favouriteRestaurantResponseDtoRestaurantDetails(buildRestaurantDetailsResponseDtoFromRestaurantDetails(favouriteRestaurant))
																									.build();
		return favouriteRestaurantResponseDto;	
	}
	
	public RestaurantDetailsResponseDto buildRestaurantDetailsResponseDtoFromRestaurantDetails(FavouriteRestaurant favouriteRestaurant) {

		ResponseEntity<RestaurantDetailsResponseDto> restaurantDetailsById = restaurantClient.getRestaurantDetailsById(favouriteRestaurant.getRestaurantId());
		RestaurantDetailsResponseDto restaurantDetailsResponseDto = RestaurantDetailsResponseDto.builder()
									.restaurantNameResponseDto(restaurantDetailsById.getBody().getRestaurantNameResponseDto())
									.restaurantOwnerNameResponseDto(restaurantDetailsById.getBody().getRestaurantOwnerNameResponseDto())
									.restaurantPhoneNumberResponseDto(restaurantDetailsById.getBody().getRestaurantPhoneNumberResponseDto())
									.restaurantTypeResponseDto(restaurantDetailsById.getBody().getRestaurantTypeResponseDto())
									.restaurantRatingResponseDto(restaurantDetailsById.getBody().getRestaurantRatingResponseDto())
									.isRestaurantOpenedResponeDto(restaurantDetailsById.getBody().isRestaurantOpenedResponeDto())
									.restaurantItemResponseDto(restaurantDetailsById.getBody().getRestaurantItemResponseDto())
									.restaurantAddressResponseDto(restaurantDetailsById.getBody().getRestaurantAddressResponseDto())
									.build();
		
		return restaurantDetailsResponseDto;
	}
	
}
