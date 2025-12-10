package com.IMTFoods.DeliveryPartnerManagement.builder;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.IMTFoods.DeliveryPartnerManagement.dto.DeliveryPartnerAssignmentResponseDto;
import com.IMTFoods.DeliveryPartnerManagement.dto.RestaurantAddressResponseDto;
import com.IMTFoods.DeliveryPartnerManagement.dto.RestaurantDetailsResponseDto;
import com.IMTFoods.DeliveryPartnerManagement.dto.UserAddressInformationResponseDto;
import com.IMTFoods.DeliveryPartnerManagement.dto.UserInformationResponseDto;
import com.IMTFoods.DeliveryPartnerManagement.model.DeliveryPartnerAssignments;

@Component
public class DeliveryPartnerAssignmentResponseBuilder {
	
	private final RestTemplate loadRestTemplate;
	
	public DeliveryPartnerAssignmentResponseBuilder(@Qualifier("loadRestTemplate") RestTemplate loadRestTemplate) {
		this.loadRestTemplate = loadRestTemplate;
	}
	
	
	public DeliveryPartnerAssignmentResponseDto buildDeliveryPartnerAssignmentResponseDtoFromDeliveryPartnerAssignment(DeliveryPartnerAssignments deliveryPartnerAssignment) {
		
		long userId = deliveryPartnerAssignment.getCurrentOrderedUserId();
		long userAddressId = deliveryPartnerAssignment.getCurrentOrderedUserAddressId();
		long restaurantId = deliveryPartnerAssignment.getCurrentRestaurantId();
		long restaurantAddressId = deliveryPartnerAssignment.getCurrentRestaurantAddressId();
		
		UserInformationResponseDto userInformation = fetchUserInformationResponseDtoFromUserManagement(userId);
		UserAddressInformationResponseDto userAddressInformation = fetchUserAddressInformationResponseDtoFromUserManagement(userAddressId);
		RestaurantDetailsResponseDto restaurantDetails = fetchRestaurantDetailsResponseDtoFromRestaurantManagement(restaurantId);
		RestaurantAddressResponseDto restaurantAddressInformation = fetchRestaurantAddressResponseDtoFromRestaurantManagement(restaurantAddressId);
				
		DeliveryPartnerAssignmentResponseDto deliveryPartnerAssignmentResponseDto = DeliveryPartnerAssignmentResponseDto.builder()
												.deliveryPartnerAssignmentResponseDtoAssignmentId(deliveryPartnerAssignment.getAssignmentId())
												.deliveryPartnerAssignmentResponseDtoAssignmentStatus(deliveryPartnerAssignment.getAssignmentStatus())
												.deliveryPartnerAssignmentResponseDtoOrderId(deliveryPartnerAssignment.getOrderId())
												.deliveryPartnerAssignmentResponseDtoRestaurantName(restaurantDetails.getRestaurantNameResponseDto())
												.deliveryPartnerAssignmentResponseDtoRestaurantPhoneNumber(restaurantDetails.getRestaurantPhoneNumberResponseDto())
												.deliveryPartnerAssignmentResponseDtoRestaurantAddress(restaurantAddressInformation)
												.deliveryPartnerAssignmentResponseDtoDeliveryUserName(addFirstNameAndLastName(userInformation))
												.deliveryPartnerAssignmentResponseDtoDeliveryUserPhoneNumber(userInformation.getUserPhoneNumberResponseDto())
												.deliveryPartnerAssignmentResponseDtoDeliveryUserAddress(userAddressInformation)
												.deliveryPartnerAssignmentResponseDtoDeliveryAssignmentCreatedAt(deliveryPartnerAssignment.getDeliveryAssignmentCreatedAt())
												.deliveryPartnerAssignmentResponseDtoEstimatedDeliveryTime(deliveryPartnerAssignment.getEstimatedDeliveryTime())
												.build();
		return deliveryPartnerAssignmentResponseDto;
	}
	
	private String addFirstNameAndLastName(UserInformationResponseDto userInformationResponseDto) {
		return userInformationResponseDto.getUserFirstNameResponseDto()+" "+userInformationResponseDto.getUserLastNameResponseDto();
	}
	
	private UserInformationResponseDto fetchUserInformationResponseDtoFromUserManagement(long userId) {
		UserInformationResponseDto userInformationResponseDto = loadRestTemplate.getForObject("http://IMTFoods-UserManagement/user/"+userId, UserInformationResponseDto.class);
		return userInformationResponseDto;
	}
	
	
	private UserAddressInformationResponseDto fetchUserAddressInformationResponseDtoFromUserManagement(long userAddressId) {
		UserAddressInformationResponseDto userAddressInformationResponseDto = loadRestTemplate.getForObject("http://IMTFoods-UserManagement/address/find/"+userAddressId, UserAddressInformationResponseDto.class);
		return userAddressInformationResponseDto;
	}
	
	private RestaurantDetailsResponseDto fetchRestaurantDetailsResponseDtoFromRestaurantManagement(long restaurantId) {
		RestaurantDetailsResponseDto restaurantDetailsResponseDto = loadRestTemplate.getForObject("http://IMTFoods-RestaurantManagement/restaurant/"+restaurantId, RestaurantDetailsResponseDto.class);
		return restaurantDetailsResponseDto;
	}
	
	private RestaurantAddressResponseDto fetchRestaurantAddressResponseDtoFromRestaurantManagement(long restaurantAddressId) {
		RestaurantAddressResponseDto restaurantAddressResponseDto = loadRestTemplate.getForObject("http://IMTFoods-RestaurantManagement/restaurant/address/find/"+restaurantAddressId, RestaurantAddressResponseDto.class);
		return restaurantAddressResponseDto;
	}


	public List<DeliveryPartnerAssignmentResponseDto> buildListOfDeliveryPartnerAssignmentResponseDtoFromDeliveryPartnerAssignment(
			List<DeliveryPartnerAssignments> listOfDeliveryPartnerAssignments) {
		
		List<DeliveryPartnerAssignmentResponseDto> deliveryPartnerAssignmentResponseDtoList = new ArrayList<>();
		for(DeliveryPartnerAssignments deliveryPartnerAssignmentRequestDto : listOfDeliveryPartnerAssignments) {
			DeliveryPartnerAssignmentResponseDto deliveryPartnerAssignmentResponseDto = buildDeliveryPartnerAssignmentResponseDtoFromDeliveryPartnerAssignment(deliveryPartnerAssignmentRequestDto);
			deliveryPartnerAssignmentResponseDtoList.add(deliveryPartnerAssignmentResponseDto);
		}
		return deliveryPartnerAssignmentResponseDtoList;
	}
	
	
}
