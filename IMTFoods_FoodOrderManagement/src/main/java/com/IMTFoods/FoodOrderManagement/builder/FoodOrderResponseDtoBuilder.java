package com.IMTFoods.FoodOrderManagement.builder;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.IMTFoods.FoodOrderManagement.dto.DeliveryPartnerAssignmentResponseDto;
import com.IMTFoods.FoodOrderManagement.dto.DeliveryPartnerDetailsResponseDto;
import com.IMTFoods.FoodOrderManagement.dto.FoodOrderResponseDto;
import com.IMTFoods.FoodOrderManagement.dto.OrderItemsResponseDto;
import com.IMTFoods.FoodOrderManagement.dto.PaymentDetailsResponseDto;
import com.IMTFoods.FoodOrderManagement.dto.RestaurantAddressResponseDto;
import com.IMTFoods.FoodOrderManagement.dto.RestaurantDetailsResponseDto;
import com.IMTFoods.FoodOrderManagement.dto.UserAddressInformationResponseDto;
import com.IMTFoods.FoodOrderManagement.dto.UserInformationResponseDto;
import com.IMTFoods.FoodOrderManagement.model.FoodOrder;
import com.IMTFoods.FoodOrderManagement.model.OrderItems;
import com.IMTFoods.FoodOrderManagement.model.PaymentDetails;

@Component
public class FoodOrderResponseDtoBuilder {
	
	private final RestTemplate restTemplate;
	private final RestTemplate loadRestTemplate;

	public FoodOrderResponseDtoBuilder(@Qualifier("restTemplate") RestTemplate restTemplate, @Qualifier("loadRestTemplate") RestTemplate loadRestTemplate) {
		this.restTemplate = restTemplate;
		this.loadRestTemplate = loadRestTemplate;
	}
	

	public FoodOrderResponseDto buildFoodOrderResponseDtoFromFoodOrder(FoodOrder foodOrder) {
		
		long userId = foodOrder.getUserId();
		long userAddressId = foodOrder.getUserAddressId();
		long restaurantId = foodOrder.getRestaurantId();
		long restaurantAddressId = foodOrder.getRestaurantAddressId();
		long deliveryPartnerId = foodOrder.getDeliveryPartnerId();
		long deliveryAssignmentId = foodOrder.getDeliveryAssignmentId();
		
		UserInformationResponseDto userInformationResponseDto = fetchOrderedUserNameFromUserManagement(userId);
		UserAddressInformationResponseDto userAddressInformationResponseDto = fetchUserAddressInformationResponseDtoFromUserManagement(userAddressId);
		RestaurantDetailsResponseDto restaurantDetailsResponseDto = fetchRestaurantNameFromRestaurantManagement(restaurantId);
		RestaurantAddressResponseDto restaurantAddressResponseDto = fetchRestaurantAddressResponseDtoFromRestaurantManagement(restaurantAddressId);
		DeliveryPartnerDetailsResponseDto deliveryPartnerDetailsResponseDto = fetchDeliveryPartnerNameFromDeliveryPartnerManagement(deliveryPartnerId);
		DeliveryPartnerAssignmentResponseDto deliveryPartnerAssignmentResponseDto = fetchDeliveryAssignmentFromOrderedFoodDetails(deliveryAssignmentId);
				
		FoodOrderResponseDto foodOrderResponseDto = FoodOrderResponseDto.builder()
							.foodOrderResponseDtoOrderId(foodOrder.getOrderId())
							.foodOrderResponseDtoTrackingId(foodOrder.getTrackingId())
							.foodOrderResponseDtoOrderedUserName(addFirstNameAndLastName(userInformationResponseDto))
							.foodOrderResponseDtoOrderedUserPhoneNumber(userInformationResponseDto.getUserPhoneNumberResponseDto())
							.foodOrderResponseDtoOrderedUserAddressDetails(userAddressInformationResponseDto) 
							.foodOrderResponseDtoRestaurantName(restaurantDetailsResponseDto.getRestaurantNameResponseDto())
							.foodOrderResponseDtoRestaurantPhoneNumber(restaurantDetailsResponseDto.getRestaurantPhoneNumberResponseDto())
							.foodOrderResponseDtoRestaurantAddress(restaurantAddressResponseDto) 
							.foodOrderResponseDtoDeliveryPartnerName(deliveryPartnerDetailsResponseDto.getDeliveryPartnerNameResponseDto())
							.foodOrderResponseDtoDeliveryPartnerPhoneNumber(deliveryPartnerDetailsResponseDto.getDeliveryPartnerPhoneNumberResponseDto())
							.foodOrderResponseDtoDeliveryPartnerAssignmentId(deliveryAssignmentId)
							.foodOrderResponseDtoDeliveryPartnerAssignmentStatus(deliveryPartnerAssignmentResponseDto.getDeliveryPartnerAssignmentResponseDtoAssignmentStatus())
							.foodOrderResponseDtoDeliveryPartnerAssignmentCreatedAt(deliveryPartnerAssignmentResponseDto.getDeliveryPartnerAssignmentResponseDtoDeliveryAssignmentCreatedAt())
							.foodOrderResponseDtoEstimatedDeliveryTime(deliveryPartnerAssignmentResponseDto.getDeliveryPartnerAssignmentResponseDtoEstimatedDeliveryTime())
							.foodOrderResponseDtoOrderItemsResponseDto(buildListOfOrderItemsResponseDtoFromOrderItems(foodOrder.getOrderItems()))
							.foodOrderResponseDtoOrderTotalPrice(foodOrder.getOrderTotalPrice())
							.foodOrderResponseDtoPaymentDetailsResponseDto(buildPaymentDetailsResponseDtoFromPaymentDetails(foodOrder.getPaymentDetails()))
							.foodOrderResponseDtoOrderStatus(foodOrder.getOrderStatus())
							.build();
							
		return foodOrderResponseDto;
	}
	
//fetching details of User from UserManagement Service related logic
	private UserInformationResponseDto fetchOrderedUserNameFromUserManagement(long userId) {
		UserInformationResponseDto userInformationResponseDto = loadRestTemplate.getForObject("http://IMTFoods-UserManagement/user/"+userId, UserInformationResponseDto.class);
		return userInformationResponseDto;
	}
	
	private String addFirstNameAndLastName(UserInformationResponseDto userInformationResponseDto) {
		return userInformationResponseDto.getUserFirstNameResponseDto() +" "+ userInformationResponseDto.getUserLastNameResponseDto();
	}

//fetching details of Restaurant from RestaurantManagement Service related logic
	private RestaurantDetailsResponseDto fetchRestaurantNameFromRestaurantManagement(long restaurantId) {
		RestaurantDetailsResponseDto restaurantDetailsResponseDto = loadRestTemplate.getForObject("http://IMTFoods-RestaurantManagement/restaurant/"+restaurantId, RestaurantDetailsResponseDto.class);
		return restaurantDetailsResponseDto;
	}

//fetching details of DeliveryPartner from DeliveryPartnerManagement Service related logic
	private DeliveryPartnerDetailsResponseDto fetchDeliveryPartnerNameFromDeliveryPartnerManagement(long deliveryPartnerId) {
		DeliveryPartnerDetailsResponseDto deliveryPartnerDetailsResponseDto = restTemplate.getForObject("http://localhost:9003/deliveryPartner/"+deliveryPartnerId, DeliveryPartnerDetailsResponseDto.class);
		return deliveryPartnerDetailsResponseDto;
	}
		
//fetching Details of DeliveryAssignment from DeliveryPartnerManagement Service related logic
	private DeliveryPartnerAssignmentResponseDto fetchDeliveryAssignmentFromOrderedFoodDetails(long deliveryPartnerAssignmentId) {
		DeliveryPartnerAssignmentResponseDto deliveryPartnerAssignmentResponseDto = restTemplate.getForObject("http://localhost:9003/deliveryassignment/assign/find/"+deliveryPartnerAssignmentId, DeliveryPartnerAssignmentResponseDto.class);
		return deliveryPartnerAssignmentResponseDto; 
	}
	
//fetching Details of User Address from UserManagement Service related logic
	private UserAddressInformationResponseDto fetchUserAddressInformationResponseDtoFromUserManagement(long userAddressId) {
		UserAddressInformationResponseDto userAddressInformationResponseDto = loadRestTemplate.getForObject("http://IMTFoods-UserManagement/address/find/"+userAddressId, UserAddressInformationResponseDto.class);
		return userAddressInformationResponseDto;
	}
	
//fetching details of Restaurant Address from RestaurantManagement Service related logic
	private RestaurantAddressResponseDto fetchRestaurantAddressResponseDtoFromRestaurantManagement(long restaurantAddressId) {
		RestaurantAddressResponseDto restaurantAddressResponseDto = loadRestTemplate.getForObject("http://IMTFoods-RestaurantManagement/restaurant/address/find/"+restaurantAddressId, RestaurantAddressResponseDto.class);
		return restaurantAddressResponseDto;
	}

//Getting Details of OrderItemsResponseDto related logic
	private static List<OrderItemsResponseDto> buildListOfOrderItemsResponseDtoFromOrderItems(List<OrderItems> orderItemsList){
		
		List<OrderItemsResponseDto> orderItemsResponseDtoList = new ArrayList<>();
		for(OrderItems orderItems : orderItemsList) {
			OrderItemsResponseDto orderItemsResponseDto = buildOrderItemsResponseDtoFromOrderItems(orderItems);
			orderItemsResponseDtoList.add(orderItemsResponseDto);
		}
		return orderItemsResponseDtoList;
	}

	public static OrderItemsResponseDto buildOrderItemsResponseDtoFromOrderItems(OrderItems orderItems) {

		OrderItemsResponseDto orderItemsResponseDto = OrderItemsResponseDto.builder()
								.orderItemResponseDtoFoodItemName(orderItems.getFoodItemName())
								.orderItemResponseDtoFoodItemPrice(orderItems.getFoodItemPrice())
								.orderItemResponseDtoFoodQuantity(orderItems.getFoodQuantity())
								.build();
		return orderItemsResponseDto;	
	}

//Getting Details of PaymentDetailsResponseDto related logic
	public static PaymentDetailsResponseDto buildPaymentDetailsResponseDtoFromPaymentDetails(
			PaymentDetails paymentDetails) {
		
		PaymentDetailsResponseDto paymentDetailsResponseDto = PaymentDetailsResponseDto.builder()
									.paymentDetailsResponseDtoPaymentTransactionId(paymentDetails.getPaymentTransactionId())
									.paymentDetailsResponseDtoOrderPaymentActualPrice(paymentDetails.getOrderPaymentActualPrice())
									.paymentDetailsResponseDtoDiscountAmount(paymentDetails.getDiscountAmount())
									.paymentDetailsResponseDtoOrderPaymentFinalPrice(paymentDetails.getOrderPaymentFinalPrice())
									.paymentDetailsResponseDtoPaymentType(paymentDetails.getPaymentType())
									.paymentDetailsResponseDtoPaymentStatus(paymentDetails.getPaymentStatus())
									.build();
		return paymentDetailsResponseDto;
	}
}
