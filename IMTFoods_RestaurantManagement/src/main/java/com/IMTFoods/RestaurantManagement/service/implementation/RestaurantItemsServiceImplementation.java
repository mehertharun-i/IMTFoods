package com.IMTFoods.RestaurantManagement.service.implementation;

import org.springframework.stereotype.Service;

import com.IMTFoods.RestaurantManagement.builder.RestaurantDetailsResponseBuilder;
import com.IMTFoods.RestaurantManagement.dao.RestaurantItemsRepository;
import com.IMTFoods.RestaurantManagement.dto.RestaurantItemsResponseDto;
import com.IMTFoods.RestaurantManagement.exception.FoodItemIdNotFoundException;
import com.IMTFoods.RestaurantManagement.model.RestaurantItems;
import com.IMTFoods.RestaurantManagement.service.RestaurantItemsService;
import com.IMTFoods.RestaurantManagement.utils.ItemsAvailableStatus;

@Service
public class RestaurantItemsServiceImplementation implements RestaurantItemsService {

	private final RestaurantItemsRepository restaurantItemsRepository;
	
	public RestaurantItemsServiceImplementation(RestaurantItemsRepository restaurantItemsRepository) {
		this.restaurantItemsRepository = restaurantItemsRepository;
	}
	
	@Override
	public RestaurantItemsResponseDto getItemDetailsById(long foodItemId) {
		
		RestaurantItems foodItemDetails = restaurantItemsRepository.findById(foodItemId).orElseThrow( () -> new FoodItemIdNotFoundException("Invalid Food Item Id "));
		RestaurantItemsResponseDto foodItemsResponseDto = RestaurantDetailsResponseBuilder.buildRestaurantItemsResponseDtoFromRestaurantDetails(foodItemDetails);
		
		return foodItemsResponseDto;
	}

	@Override
	public ItemsAvailableStatus updateFoodItemsAvailablility(long foodItemId, ItemsAvailableStatus availableStatus) {
		
		RestaurantItems restaurantItems = restaurantItemsRepository.findById(foodItemId).orElseThrow( () -> new FoodItemIdNotFoundException("Invalid Food Item Id"));
		restaurantItems.setFoodItemAvailableStatus(availableStatus);
		RestaurantItems updatedFoodItemStatus = restaurantItemsRepository.save(restaurantItems);
		return updatedFoodItemStatus.getFoodItemAvailableStatus();
	}

}
