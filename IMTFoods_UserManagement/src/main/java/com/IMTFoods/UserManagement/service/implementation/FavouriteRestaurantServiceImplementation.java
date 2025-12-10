package com.IMTFoods.UserManagement.service.implementation;

import org.springframework.stereotype.Service;

import com.IMTFoods.UserManagement.builder.FavouriteRestaurantBuilder;
import com.IMTFoods.UserManagement.builder.FavouriteRestaurantResponseBuilder;
import com.IMTFoods.UserManagement.dao.FavouriteRestaurantRepository;
import com.IMTFoods.UserManagement.dto.FavouriteRestaurantResponseDto;
import com.IMTFoods.UserManagement.model.FavouriteRestaurant;
import com.IMTFoods.UserManagement.service.FavouriteRestaurantService;

@Service
public class FavouriteRestaurantServiceImplementation implements FavouriteRestaurantService{

	private final FavouriteRestaurantRepository favouriteRestaurantRepository;
	
	public FavouriteRestaurantServiceImplementation(FavouriteRestaurantRepository favouriteRestaurantRepository) {
		this.favouriteRestaurantRepository = favouriteRestaurantRepository;
	}

	@Override
	public FavouriteRestaurantResponseDto addRestaurantToFavouriteList(long userInformationId, long restaurantId,
			boolean favourite) {
		FavouriteRestaurant favouriteRestaurant = FavouriteRestaurantBuilder.buildFavouriteRestaurantFromParameters(userInformationId, restaurantId, favourite);
		FavouriteRestaurant savedFavouriteRestaurant = favouriteRestaurantRepository.save(favouriteRestaurant);
		FavouriteRestaurantResponseDto favouriteRestaurantResponseDto = FavouriteRestaurantResponseBuilder.buildFavouriteRestaurantResponseDtoFormFavouriteRestaurant(savedFavouriteRestaurant);
				
		return null;
	}

	@Override
	public void removeRestaurantFromFavouriteList(long userInformationId, long restaurantId, boolean favourite) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
