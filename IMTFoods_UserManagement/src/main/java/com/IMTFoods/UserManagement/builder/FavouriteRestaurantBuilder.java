package com.IMTFoods.UserManagement.builder;

import org.springframework.context.annotation.Configuration;

import com.IMTFoods.UserManagement.model.FavouriteRestaurant;
import com.IMTFoods.UserManagement.model.UserInformation;

@Configuration
public class FavouriteRestaurantBuilder {

	public static FavouriteRestaurant buildFavouriteRestaurantFromParameters(UserInformation userInformation, long restaurantId, boolean favourite) {
		
		FavouriteRestaurant favouriteRestaurant = FavouriteRestaurant.builder()
							.userInformationId(userInformation)
							.restaurantId(restaurantId)
							.isFavourite(favourite)
							.build();
		
		return favouriteRestaurant;
	}
	
}
