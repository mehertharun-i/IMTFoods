package com.IMTFoods.UserManagement.service;

import com.IMTFoods.UserManagement.dto.FavouriteRestaurantResponseDto;

public interface FavouriteRestaurantService {

	FavouriteRestaurantResponseDto addRestaurantToFavouriteList(long userInformationId, long restaurantId, boolean favourite);

	void removeRestaurantFromFavouriteList(long userInformationId, long restaurantId, boolean favourite);

}
