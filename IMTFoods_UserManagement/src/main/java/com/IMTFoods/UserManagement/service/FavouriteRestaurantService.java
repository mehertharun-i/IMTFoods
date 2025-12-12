package com.IMTFoods.UserManagement.service;

import java.util.List;

import com.IMTFoods.UserManagement.dto.FavouriteRestaurantResponseDto;

public interface FavouriteRestaurantService {

	FavouriteRestaurantResponseDto addRestaurantToFavouriteList(long userInformationId, long restaurantId, boolean favourite);

	void removeRestaurantFromFavouriteList(long favouriteId);

	FavouriteRestaurantResponseDto getFavouriteRestauratnById(long favouriteId);

	List<FavouriteRestaurantResponseDto> getAllFavouriteRestaurantList();

}
