package com.IMTFoods.DeliveryPartnerManagement.builder;

import org.springframework.web.client.RestTemplate;

import com.IMTFoods.DeliveryPartnerManagement.dto.DeliveryPartnerAssignmentResponseDto;
import com.IMTFoods.DeliveryPartnerManagement.dto.RestaurantAddressResponseDto;
import com.IMTFoods.DeliveryPartnerManagement.dto.RestaurantDetailsResponseDto;
import com.IMTFoods.DeliveryPartnerManagement.dto.UserAddressInformationResponseDto;
import com.IMTFoods.DeliveryPartnerManagement.dto.UserInformationResponseDto;
import com.IMTFoods.DeliveryPartnerManagement.model.DeliveryPartnerAssignments;

public class DeliveryPartnerAssignmentResponseBuilder {
	
	private final RestTemplate restTemplate;
	
	public DeliveryPartnerAssignmentResponseBuilder(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	public DeliveryPartnerAssignmentResponseBuilder() {
		this.restTemplate = new RestTemplate();
	}

	private static UserInformationResponseDto userInformationResponseDto;
	private static RestaurantDetailsResponseDto restaurantDetailsResponseDto;
	
	public static DeliveryPartnerAssignmentResponseDto buildDeliveryPartnerAssignmentResponseDtoFromDeliveryPartnerAssignment(DeliveryPartnerAssignments deliveryPartnerAssignment) {
		
		DeliveryPartnerAssignmentResponseBuilder deliveryPartnerAssignmentResponseBuilder = new DeliveryPartnerAssignmentResponseBuilder();
		
		DeliveryPartnerAssignmentResponseDto deliveryPartnerAssignmentResponseDto = DeliveryPartnerAssignmentResponseDto.builder()
												.deliveryPartnerAssignmentResponseDtoAssignmentId(deliveryPartnerAssignment.getAssignmentId())
												.deliveryPartnerAssignmentResponseDtoAssignmentStatus(deliveryPartnerAssignment.getAssignmentStatus())
												.deliveryPartnerAssignmentResponseDtoOrderId(deliveryPartnerAssignment.getOrderId())
												.deliveryPartnerAssignmentResponseDtoRestaurantName(deliveryPartnerAssignmentResponseBuilder.fetchRestaurantDetailsResponseDtoFromRestaurantManagement(deliveryPartnerAssignment.getCurrentRestaurantId()))
												.deliveryPartnerAssignmentResponseDtoRestaurantPhoneNumber(restaurantDetailsResponseDto.getRestaurantPhoneNumberResponseDto())
												.deliveryPartnerAssignmentResponseDtoRestaurantAddress(deliveryPartnerAssignmentResponseBuilder.fetchRestaurantAddressResponseDtoFromRestaurantManagement(deliveryPartnerAssignment.getCurrentRestaurantAddressId()))
												.deliveryPartnerAssignmentResponseDtoDeliveryUserName(deliveryPartnerAssignmentResponseBuilder.fetchUserInformationResponseDtoFromUserManagament(deliveryPartnerAssignment.getCurrentOrderedUserId()))
												.deliveryPartnerAssignmentResponseDtoDeliveryUserPhoneNumber(userInformationResponseDto.getUserPhoneNumberResponseDto())
												.deliveryPartnerAssignmentResponseDtoDeliveryUserAddress(deliveryPartnerAssignmentResponseBuilder.fetchUserAddressInformationResponseDtoFromUserManagement(deliveryPartnerAssignment.getCurrentOrderedUserAddressId()))
												.deliveryPartnerAssignmentResponseDtoDeliveryAssignmentCreatedAt(deliveryPartnerAssignment.getDeliveryAssignmentCreatedAt())
												.deliveryPartnerAssignmentResponseDtoEstimatedDeliveryTime(deliveryPartnerAssignment.getEstimatedDeliveryTime())
												.build();
		return deliveryPartnerAssignmentResponseDto;
	}
	
	private String fetchUserInformationResponseDtoFromUserManagament(long userId) {
		userInformationResponseDto = restTemplate.getForObject("http://localhost:9001/user/"+userId, UserInformationResponseDto.class);
		String userName = userInformationResponseDto.getUserFirstNameResponseDto() +" "+ userInformationResponseDto.getUserLastNameResponseDto();
		return userName;
	}
	
	private UserAddressInformationResponseDto fetchUserAddressInformationResponseDtoFromUserManagement(long userAddressId) {
		UserAddressInformationResponseDto userAddressInformationResponseDto = restTemplate.getForObject("http://localhost:9001/user/address/find/"+userAddressId, UserAddressInformationResponseDto.class);
		return userAddressInformationResponseDto;
	}
	
	
	private String fetchRestaurantDetailsResponseDtoFromRestaurantManagement(long restaurantId) {
		restaurantDetailsResponseDto = restTemplate.getForObject("http://localhost:9002/restaurant/"+restaurantId, RestaurantDetailsResponseDto.class);
		return restaurantDetailsResponseDto.getRestaurantNameResponseDto();
	}
	
	private RestaurantAddressResponseDto fetchRestaurantAddressResponseDtoFromRestaurantManagement(long restaurantAddressId) {
		RestaurantAddressResponseDto restaurantAddressResponseDto = restTemplate.getForObject("http://localhost:9002/restaurant/address/find/"+restaurantAddressId, RestaurantAddressResponseDto.class);
		return restaurantAddressResponseDto;
	}
	
	
}
