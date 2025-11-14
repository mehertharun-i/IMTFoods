package com.IMTFoods.RestaurantManagement.builder;

import java.util.ArrayList;
import java.util.List;

import com.IMTFoods.RestaurantManagement.dto.RestaurantAddressRequestDto;
import com.IMTFoods.RestaurantManagement.dto.RestaurantDetailsRequestDto;
import com.IMTFoods.RestaurantManagement.dto.RestaurantItemsRequestDto;
import com.IMTFoods.RestaurantManagement.model.RestaurantAddress;
import com.IMTFoods.RestaurantManagement.model.RestaurantDetails;
import com.IMTFoods.RestaurantManagement.model.RestaurantItems;

public class RestaurantDetailsBuilder {

	public static RestaurantDetails buildRestaurantDetailsFromRestaurantDetailsRequestDto(RestaurantDetailsRequestDto restaurantDetailsRequestDto) {
		
		RestaurantDetails restaurantDetails = RestaurantDetails.builder()
									.restaurantName(restaurantDetailsRequestDto.getRestaurantNameRequestDto())
									.restaurantOwnerName(restaurantDetailsRequestDto.getRestaurantOwnerNameRequestDto())
									.restaurantType(restaurantDetailsRequestDto.getRestaurantTypeRequestDto())
									.restaurantRating(restaurantDetailsRequestDto.getRestaurantRatingRequestDto())
									.isRestaurantOpened(true)
									.restaurantItems(RestaurantDetailsBuilder.buildListOfRestaurantItemsFromRestaurantItemsRequestDto(restaurantDetailsRequestDto.getRestaurantItemsRequestDto()))
									.restaurantAddress(RestaurantDetailsBuilder.buildListOfRestaurantAddressFromRestaurantAddressRequestDto(restaurantDetailsRequestDto.getRestaurantAddressRequestDto()))
									.build();
		
		linkRestaurantDetailsRequestDtoToRestaurantDetails(restaurantDetails);
		
		return restaurantDetails;
		
	}
	
	private static void linkRestaurantDetailsRequestDtoToRestaurantDetails(RestaurantDetails restaurantDetails) {

		if(restaurantDetails.getRestaurantItems() != null) {
			for(RestaurantItems restaurantItems : restaurantDetails.getRestaurantItems()) {
				restaurantItems.setRestaurantDetailsId(restaurantDetails);
			}
		}
		
		if(restaurantDetails.getRestaurantAddress() != null) {
			for(RestaurantAddress restaurantAddress : restaurantDetails.getRestaurantAddress()){
				restaurantAddress.setRestaurantDetailsId(restaurantDetails);
			}
		}
		
	}

	private static List<RestaurantItems> buildListOfRestaurantItemsFromRestaurantItemsRequestDto(List<RestaurantItemsRequestDto> restaurantItemsRequestDtoList) {
		
		List<RestaurantItems> restaurantItemsList = new ArrayList<>();
		
		for(RestaurantItemsRequestDto restaurantItemsRequestDto : restaurantItemsRequestDtoList) {
			RestaurantItems restaurantItems = buildRestaurantItemsFromRestaurantItemsRequestDto(restaurantItemsRequestDto);
			restaurantItemsList.add(restaurantItems);
		}
		
		return restaurantItemsList;
	}
	
	private static List<RestaurantAddress> buildListOfRestaurantAddressFromRestaurantAddressRequestDto(List<RestaurantAddressRequestDto> restaurantAddressRequestDtoList) {
		
		List<RestaurantAddress> restaurantAddressList = new ArrayList<>();
		
		for(RestaurantAddressRequestDto restaurantAddressRequestDto : restaurantAddressRequestDtoList) {
			RestaurantAddress restaurantAddress = buildRestaurantAddressFromRestaurantAddressRequestDto(restaurantAddressRequestDto);
			restaurantAddressList.add(restaurantAddress);
		}
		
		return restaurantAddressList;
	}
	
	public static RestaurantItems buildRestaurantItemsFromRestaurantItemsRequestDto(RestaurantItemsRequestDto restaurantItemsRequestDto) {
		
		RestaurantItems restaurantItems = RestaurantItems.builder()
											.itemName(restaurantItemsRequestDto.getItemNameRequestDto())
											.itemPrice(restaurantItemsRequestDto.getItemPriceRequestDto())
											.itemCategory(restaurantItemsRequestDto.getItemCategoryRequestDto())
											.itemRating(restaurantItemsRequestDto.getItemRatingRequestDto())
											.itemDescription(restaurantItemsRequestDto.getItemDescriptionRequestDto())
											.itemType(restaurantItemsRequestDto.getItemTypeRequestDto())
											.build();
		return restaurantItems;
	}
	
	public static RestaurantAddress buildRestaurantAddressFromRestaurantAddressRequestDto(RestaurantAddressRequestDto restaurantAddressReqeustDto) {
		
		RestaurantAddress restaurantAddress = RestaurantAddress.builder()
												.restaurantHouseNumber(restaurantAddressReqeustDto.getRestaurantHouseNumberRequestDto())
												.restaurantStreet(restaurantAddressReqeustDto.getRestaurantStreetRequestDto())
												.restaurantLandMark(restaurantAddressReqeustDto.getRestaurantLandMarkRequestDto())
												.restaurantDistrict(restaurantAddressReqeustDto.getRestaurantDistrictRequestDto())
												.restaurantState(restaurantAddressReqeustDto.getRestaurantStateRequestDto())
												.restaurantCountry(restaurantAddressReqeustDto.getRestaurantCountryRequestDto())
												.restaurantPincode(restaurantAddressReqeustDto.getRestaurantPincodeRequestDto())
												.build();
		return restaurantAddress;
	}

}
