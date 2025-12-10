package com.IMTFoods.UserManagement.builder;

import org.springframework.context.annotation.Configuration;

import com.IMTFoods.UserManagement.model.FavouriteRestaurant;

@Configuration
public class FavouriteRestaurantBuilder {

	public static FavouriteRestaurant buildFavouriteRestaurantFromParameters(long userInformationId, long restaurantId, boolean favourite) {
		
		FavouriteRestaurant favouriteRestaurant = FavouriteRestaurant.builder()
							.userInformationId(userInformationId)
							.restaurantId(restaurantId)
							.isFavourite(favourite)
							.build();
		
		return favouriteRestaurant;
	}
	
}
