package com.IMTFoods.RestaurantManagement.builder;

import java.util.ArrayList;
import java.util.List;

import com.IMTFoods.RestaurantManagement.dto.RestaurantAddressResponseDto;
import com.IMTFoods.RestaurantManagement.dto.RestaurantDetailsResponseDto;
import com.IMTFoods.RestaurantManagement.dto.RestaurantItemsResponseDto;
import com.IMTFoods.RestaurantManagement.model.RestaurantAddress;
import com.IMTFoods.RestaurantManagement.model.RestaurantDetails;
import com.IMTFoods.RestaurantManagement.model.RestaurantItems;

public class RestaurantDetailsResponseBuilder {

	public static RestaurantDetailsResponseDto buildRestaurantDetailsFromRestaurantDetailsResponseDto(
			RestaurantDetails savedRestaurantDetails) {

		RestaurantDetailsResponseDto restaurantDetailsResponseDto = RestaurantDetailsResponseDto.builder()
									.restaurantNameResponseDto(savedRestaurantDetails.getRestaurantName())
									.restaurantOwnerNameResponseDto(savedRestaurantDetails.getRestaurantOwnerName())
									.restaurantPhoneNumberResponseDto(savedRestaurantDetails.getRestaurantPhoneNumber())
									.restaurantTypeResponseDto(savedRestaurantDetails.getRestaurantType())
									.restaurantRatingResponseDto(savedRestaurantDetails.getRestaurantRating())
									.isRestaurantOpenedResponeDto(savedRestaurantDetails.isRestaurantOpened())
									.restaurantItemResponseDto(RestaurantDetailsResponseBuilder.buildListOfRestaurantItemsResponseDtoFromRestaurantDetails(savedRestaurantDetails.getRestaurantItems()))
									.restaurantAddressResponseDto(RestaurantDetailsResponseBuilder.buildListOfRestaurantAddressResponseDtoFromRestaurantDetails(savedRestaurantDetails.getRestaurantAddress()))
									.build();
		return restaurantDetailsResponseDto;
	}
	
	private static List<RestaurantItemsResponseDto> buildListOfRestaurantItemsResponseDtoFromRestaurantDetails(List<RestaurantItems> restaurantItemsList){
		
		List<RestaurantItemsResponseDto> restaurantItemResponseDtoList = new ArrayList<>();
		
		for(RestaurantItems restaurantItem : restaurantItemsList) {
			RestaurantItemsResponseDto restaurantItemsResponseDto = buildRestaurantItemsResponseDtoFromRestaurantDetails(restaurantItem);
			restaurantItemResponseDtoList.add(restaurantItemsResponseDto);
		}
		
		return restaurantItemResponseDtoList;
	}
	
	private static List<RestaurantAddressResponseDto> buildListOfRestaurantAddressResponseDtoFromRestaurantDetails(List<RestaurantAddress> restaurantAddressList) {
		
		List<RestaurantAddressResponseDto> restaurantAddressResponseDtoList = new ArrayList<>();
		
		for(RestaurantAddress restaurantAddress : restaurantAddressList) {
			RestaurantAddressResponseDto restaurantAddressResponseDto = buildRestaurantAddressResponseDtoFromRestaurantDetails(restaurantAddress);
			restaurantAddressResponseDtoList.add(restaurantAddressResponseDto);
		}
		
		return restaurantAddressResponseDtoList;
	}



	public static RestaurantItemsResponseDto buildRestaurantItemsResponseDtoFromRestaurantDetails(RestaurantItems restaurantItem) {

		RestaurantItemsResponseDto restaurantItemsResponseDto = RestaurantItemsResponseDto.builder()
									.itemNameResponseDto(restaurantItem.getItemName())
									.itemPriceResponseDto(restaurantItem.getItemPrice())
									.itemCategoryResponseDto(restaurantItem.getItemCategory())
									.itemRatingResponseDto(restaurantItem.getItemRating())
									.itemDescriptionResponseDto(restaurantItem.getItemDescription())
									.itemTypeResponseDto(restaurantItem.getItemType())
									.build();
		
		return restaurantItemsResponseDto;
	}

	public static RestaurantAddressResponseDto buildRestaurantAddressResponseDtoFromRestaurantDetails(RestaurantAddress restaurantAddress) {
		
		RestaurantAddressResponseDto restaurantAddressResponseDto = RestaurantAddressResponseDto.builder()
									.restaurantHouseNumberResponseDto(restaurantAddress.getRestaurantHouseNumber())
									.restaurantStreetResponseDto(restaurantAddress.getRestaurantStreet())
									.restaurantLandMarkResponseDto(restaurantAddress.getRestaurantLandMark())
									.restaurantDistrictResponseDto(restaurantAddress.getRestaurantDistrict())
									.restaurantStateResponseDto(restaurantAddress.getRestaurantState())
									.restaurantCountryResponseDto(restaurantAddress.getRestaurantCountry())
									.restaurantPincodeResponseDto(restaurantAddress.getRestaurantPincode())
									.build();
		
		return restaurantAddressResponseDto;
	}
}
