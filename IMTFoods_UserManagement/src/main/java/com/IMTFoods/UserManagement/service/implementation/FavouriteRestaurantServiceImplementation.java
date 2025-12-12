package com.IMTFoods.UserManagement.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.IMTFoods.UserManagement.builder.FavouriteRestaurantBuilder;
import com.IMTFoods.UserManagement.builder.FavouriteRestaurantResponseBuilder;
import com.IMTFoods.UserManagement.dao.FavouriteRestaurantRepository;
import com.IMTFoods.UserManagement.dao.UserInformationRepository;
import com.IMTFoods.UserManagement.dto.FavouriteRestaurantResponseDto;
import com.IMTFoods.UserManagement.exception.FavouriteRestaurantIdNotFoundException;
import com.IMTFoods.UserManagement.exception.UserIdNotFoundException;
import com.IMTFoods.UserManagement.model.FavouriteRestaurant;
import com.IMTFoods.UserManagement.model.UserInformation;
import com.IMTFoods.UserManagement.service.FavouriteRestaurantService;

@Service
public class FavouriteRestaurantServiceImplementation implements FavouriteRestaurantService{

	private final FavouriteRestaurantRepository favouriteRestaurantRepository;
	private final UserInformationRepository userInformationRepository;
	private final FavouriteRestaurantResponseBuilder favouriteRestaurantResponseBuilder; 
	
	public FavouriteRestaurantServiceImplementation(FavouriteRestaurantRepository favouriteRestaurantRepository, FavouriteRestaurantResponseBuilder favouriteRestaurantResponseBuilder, UserInformationRepository userInformationRepository) {
		this.favouriteRestaurantRepository = favouriteRestaurantRepository;
		this.favouriteRestaurantResponseBuilder = favouriteRestaurantResponseBuilder;
		this.userInformationRepository = userInformationRepository;
	}

	@Override
	public FavouriteRestaurantResponseDto addRestaurantToFavouriteList(long userInformationId, long restaurantId,
			boolean favourite) {
		
		UserInformation userInformation = userInformationRepository.findById(userInformationId).orElseThrow( () -> new UserIdNotFoundException("Invalid User Id"));
		
		FavouriteRestaurant favouriteRestaurant = FavouriteRestaurantBuilder.buildFavouriteRestaurantFromParameters(userInformation, restaurantId, favourite);
		FavouriteRestaurant savedFavouriteRestaurant = favouriteRestaurantRepository.save(favouriteRestaurant);
		FavouriteRestaurantResponseDto favouriteRestaurantResponseDto = favouriteRestaurantResponseBuilder.buildFavouriteRestaurantResponseDtoFormFavouriteRestaurant(savedFavouriteRestaurant);
		return favouriteRestaurantResponseDto;
	}

	@Override
	public void removeRestaurantFromFavouriteList(long favouriteId) {
		FavouriteRestaurant favouriteRestaurant = favouriteRestaurantRepository.findById(favouriteId).orElseThrow( () -> new FavouriteRestaurantIdNotFoundException("Invalid Favourite Restaurant Id"));
		favouriteRestaurantRepository.deleteById(favouriteRestaurant.getFavouriteRestaurantId());		
	}

	@Override
	public FavouriteRestaurantResponseDto getFavouriteRestauratnById(long favouriteId) {
		FavouriteRestaurant favouriteRestaurant = favouriteRestaurantRepository.findById(favouriteId).orElseThrow( () -> new FavouriteRestaurantIdNotFoundException("Invalid Favourite Restaurant Id"));
		FavouriteRestaurantResponseDto favouriteRestaurantResponseDto = favouriteRestaurantResponseBuilder.buildFavouriteRestaurantResponseDtoFormFavouriteRestaurant(favouriteRestaurant);
		
		return favouriteRestaurantResponseDto;
	}

	@Override
	public List<FavouriteRestaurantResponseDto> getAllFavouriteRestaurantList() {
		
		List<FavouriteRestaurant> allFavouriteRestaurantList = favouriteRestaurantRepository.findAll();
		List<FavouriteRestaurantResponseDto> favouriteRestaurantResponseDtoList = new ArrayList<>();
		
		for(FavouriteRestaurant favouriteRestaurant : allFavouriteRestaurantList) {
			FavouriteRestaurantResponseDto favouriteRestaurantResponseDto = favouriteRestaurantResponseBuilder.buildFavouriteRestaurantResponseDtoFormFavouriteRestaurant(favouriteRestaurant);
			favouriteRestaurantResponseDtoList.add(favouriteRestaurantResponseDto);
		}
		
		return favouriteRestaurantResponseDtoList;
	}
	
	
	
}
