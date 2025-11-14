package com.IMTFoods.RestaurantManagement.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.IMTFoods.RestaurantManagement.builder.ListOfRestaurantDetailsResponseBuilder;
import com.IMTFoods.RestaurantManagement.builder.RestaurantDetailsBuilder;
import com.IMTFoods.RestaurantManagement.builder.RestaurantDetailsResponseBuilder;
import com.IMTFoods.RestaurantManagement.dao.RestaurantDetailsRepository;
import com.IMTFoods.RestaurantManagement.dto.RestaurantAddressRequestDto;
import com.IMTFoods.RestaurantManagement.dto.RestaurantDetailsRequestDto;
import com.IMTFoods.RestaurantManagement.dto.RestaurantDetailsResponseDto;
import com.IMTFoods.RestaurantManagement.dto.RestaurantItemsRequestDto;
import com.IMTFoods.RestaurantManagement.exception.RestaurantDetailsNotFoundException;
import com.IMTFoods.RestaurantManagement.model.RestaurantAddress;
import com.IMTFoods.RestaurantManagement.model.RestaurantDetails;
import com.IMTFoods.RestaurantManagement.model.RestaurantItems;
import com.IMTFoods.RestaurantManagement.service.RestaurantDetailsService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RestaurantDetailsServiceImplementation implements RestaurantDetailsService{

	private final RestaurantDetailsRepository restaurantDetailsRepository;
	
	public RestaurantDetailsServiceImplementation(RestaurantDetailsRepository restaurantDetailsRepository) {
		this.restaurantDetailsRepository = restaurantDetailsRepository;
	}
	
	@Override
	public ResponseEntity<RestaurantDetailsResponseDto> registerRestaurantDetails(RestaurantDetailsRequestDto restaurantDetailsRequestDto) {

		RestaurantDetails restaurantDetails = RestaurantDetailsBuilder.buildRestaurantDetailsFromRestaurantDetailsRequestDto(restaurantDetailsRequestDto);
		
		RestaurantDetails savedRestaurantDetails = restaurantDetailsRepository.save(restaurantDetails);
		
		RestaurantDetailsResponseDto restaurantDetailsResponseDto = RestaurantDetailsResponseBuilder.buildRestaurantDetailsFromRestaurantDetailsResponseDto(savedRestaurantDetails);
		
		return ResponseEntity.ok(restaurantDetailsResponseDto);
	}

	@Override
	public ResponseEntity<RestaurantDetailsResponseDto> getRestaurantDetailsById(long restaurantId) {
		
		RestaurantDetails restaurantDetails = restaurantDetailsRepository.findById(restaurantId).orElseThrow( () -> new RestaurantDetailsNotFoundException("Invalid Restaurant Details"));
		
		RestaurantDetailsResponseDto restaurantDetailsResponseDto = RestaurantDetailsResponseBuilder.buildRestaurantDetailsFromRestaurantDetailsResponseDto(restaurantDetails);
		
		return ResponseEntity.ok(restaurantDetailsResponseDto);
	}

	@Override
	public ResponseEntity<List<RestaurantDetailsResponseDto>> getAllRestaurantDetails() {
		
		List<RestaurantDetails> allRestaurantDetails = restaurantDetailsRepository.findAll();
		
		List<RestaurantDetailsResponseDto> listOfRestaurantDetailsResponseBuilder = ListOfRestaurantDetailsResponseBuilder.buildListOfRestaurantDetailsResponseBuilder(allRestaurantDetails);
		
		return ResponseEntity.ok(listOfRestaurantDetailsResponseBuilder);
	}

	@Override
	public ResponseEntity<Void> deleteRestaurantById(long restaurantId) {
		
		RestaurantDetails restaurantDetails = restaurantDetailsRepository.findById(restaurantId).orElseThrow( () -> new RestaurantDetailsNotFoundException("Invalid Restaurant Details"));
				
		restaurantDetailsRepository.deleteById(restaurantDetails.getRestaurantId());
		
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<RestaurantDetailsResponseDto> updateRestaurantDetailsById(long restaurantId, RestaurantDetailsRequestDto restaurantDetailsRequestDto) {
		
		RestaurantDetails restaurantDetails = restaurantDetailsRepository.findById(restaurantId).orElseThrow( () -> new RestaurantDetailsNotFoundException("Invalid Restaurant Details"));
		restaurantDetails.setRestaurantName(restaurantDetailsRequestDto.getRestaurantNameRequestDto());
		restaurantDetails.setRestaurantOwnerName(restaurantDetailsRequestDto.getRestaurantOwnerNameRequestDto());
		restaurantDetails.setRestaurantType(restaurantDetailsRequestDto.getRestaurantTypeRequestDto());
		restaurantDetails.setRestaurantRating(restaurantDetailsRequestDto.getRestaurantRatingRequestDto());
		restaurantDetails.setRestaurantOpened(true);
		
//RestaurantItems List 
		
		List<RestaurantItems> restaurantItemsList = new ArrayList<>();
		int count = 0;

		for(RestaurantItemsRequestDto restaurantItemRequestDto : restaurantDetailsRequestDto.getRestaurantItemsRequestDto()) {
			RestaurantItems restaurantItems = restaurantDetails.getRestaurantItems().get(count++);
			
			restaurantItems.setRestaurantItemId(restaurantItems.getRestaurantItemId());
			
			restaurantItems.setItemName(restaurantItemRequestDto.getItemNameRequestDto());
			restaurantItems.setItemPrice(restaurantItemRequestDto.getItemPriceRequestDto());
			restaurantItems.setItemCategory(restaurantItemRequestDto.getItemCategoryRequestDto());
			restaurantItems.setItemRating(restaurantItemRequestDto.getItemRatingRequestDto());
			restaurantItems.setItemDescription(restaurantItemRequestDto.getItemDescriptionRequestDto());
			restaurantItems.setItemType(restaurantItemRequestDto.getItemTypeRequestDto());
//			restaurantDetails.addRestaurantItem(restaurantItems);
			restaurantItemsList.add(restaurantItems);
		
		}
		restaurantDetails.getRestaurantItems().clear();
		restaurantDetails.getRestaurantItems().addAll(restaurantItemsList);

		
//RestaurantAddress List
		
		List<RestaurantAddress> restaurantAddressList = new ArrayList<>();
		count = 0;
		
		for(RestaurantAddressRequestDto restaurantAddressRequestDto : restaurantDetailsRequestDto.getRestaurantAddressRequestDto()) {
			RestaurantAddress restaurantAddress = restaurantDetails.getRestaurantAddress().get(count++);
			
			restaurantAddress.setRestaurantAddressId(restaurantAddress.getRestaurantAddressId());
			
			restaurantAddress.setRestaurantHouseNumber(restaurantAddressRequestDto.getRestaurantHouseNumberRequestDto());
			restaurantAddress.setRestaurantStreet(restaurantAddressRequestDto.getRestaurantStreetRequestDto());
			restaurantAddress.setRestaurantLandMark(restaurantAddressRequestDto.getRestaurantLandMarkRequestDto());
			restaurantAddress.setRestaurantDistrict(restaurantAddressRequestDto.getRestaurantDistrictRequestDto());
			restaurantAddress.setRestaurantState(restaurantAddressRequestDto.getRestaurantStateRequestDto());
			restaurantAddress.setRestaurantCountry(restaurantAddressRequestDto.getRestaurantCountryRequestDto());
			restaurantAddress.setRestaurantPincode(restaurantAddressRequestDto.getRestaurantPincodeRequestDto());
//			restaurantDetails.addRestaurantAddress(restaurantAddress);
			restaurantAddressList.add(restaurantAddress);
		}
		restaurantDetails.getRestaurantAddress().clear();
		restaurantDetails.getRestaurantAddress().addAll(restaurantAddressList);
		
		RestaurantDetails updatedRestaurantDetails = restaurantDetailsRepository.save(restaurantDetails);
		
		RestaurantDetailsResponseDto restaurantDetailsResponseDto = RestaurantDetailsResponseBuilder.buildRestaurantDetailsFromRestaurantDetailsResponseDto(updatedRestaurantDetails);
		
		return ResponseEntity.ok(restaurantDetailsResponseDto);
	}

}
