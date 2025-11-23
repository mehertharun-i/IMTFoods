package com.IMTFoods.RestaurantManagement.service.implementation;

import org.springframework.stereotype.Service;

import com.IMTFoods.RestaurantManagement.builder.RestaurantAddressResponseDtoBuilder;
import com.IMTFoods.RestaurantManagement.dao.RestaurantAddressRepository;
import com.IMTFoods.RestaurantManagement.dto.RestaurantAddressResponseDto;
import com.IMTFoods.RestaurantManagement.exception.RestaurantAddressIdNotFoundException;
import com.IMTFoods.RestaurantManagement.model.RestaurantAddress;
import com.IMTFoods.RestaurantManagement.service.RestaurantAddressService;

@Service
public class RestaurantAddressServiceImplementation implements RestaurantAddressService{

	private final RestaurantAddressRepository restaurantAddressRepository;
	
	public RestaurantAddressServiceImplementation(RestaurantAddressRepository restaurantAddressRepository) {
		this.restaurantAddressRepository = restaurantAddressRepository;
	}
	
	@Override
	public RestaurantAddressResponseDto getRestaurantAddressInformationById(long restaurantAddressId) {
		RestaurantAddress restaurantAddress = restaurantAddressRepository.findById(restaurantAddressId).orElseThrow( () -> new RestaurantAddressIdNotFoundException("Invalid Restaurant Address Id"));
		RestaurantAddressResponseDto restaurantAddressResponseDto = RestaurantAddressResponseDtoBuilder.buildRestaurantAddressResponseDtoFromRestaurantAddress(restaurantAddress);
		return restaurantAddressResponseDto;
	}

}
