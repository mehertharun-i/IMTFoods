package com.IMTFoods.FoodOrderManagement.builder;

import java.util.ArrayList;
import java.util.List;

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
import com.IMTFoods.FoodOrderManagement.utils.AssignmentStatus;

public class FoodOrderResponseDtoBuilder {
	
	private final RestTemplate restTemplate;
	private static UserInformationResponseDto userInformationResponseDto;
	private static RestaurantDetailsResponseDto restaurantDetailsResponseDto;
	private static DeliveryPartnerDetailsResponseDto deliveryPartnerDetailsResponseDto; 
	public static DeliveryPartnerAssignmentResponseDto deliveryPartnerAssignmentResponseDto;
	
	public FoodOrderResponseDtoBuilder(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	public FoodOrderResponseDtoBuilder() {
		this.restTemplate = new RestTemplate();
	}
	

	public static FoodOrderResponseDto buildFoodOrderResponseDtoFromFoodOrder(FoodOrder foodOrder) {
		
		FoodOrderResponseDtoBuilder foodOrderResponseDtoBuilder = new FoodOrderResponseDtoBuilder();
		
		FoodOrderResponseDto foodOrderResponseDto = FoodOrderResponseDto.builder()
							.foodOrderResponseDtoOrderId(foodOrder.getOrderId())
							.foodOrderResponseDtoTrackingId(foodOrder.getTrackingId())
							.foodOrderResponseDtoOrderedUserName(foodOrderResponseDtoBuilder.fetchOrderedUserNameFromUserManagement(foodOrder.getUserId()))
							.foodOrderResponseDtoOrderedUserPhoneNumber(userInformationResponseDto.getUserPhoneNumberResponseDto())
							.foodOrderResponseDtoOrderedUserAddressDetails(foodOrderResponseDtoBuilder.fetchUserAddressInformationResponseDtoFromUserManagement(foodOrder.getUserAddressId())) 
							.foodOrderResponseDtoRestaurantName(foodOrderResponseDtoBuilder.fetchRestaurantNameFromRestaurantManagement(foodOrder.getRestaurantId()))
							.foodOrderResponseDtoRestaurantPhoneNumber(restaurantDetailsResponseDto.getRestaurantPhoneNumberResponseDto())
							.foodOrderResponseDtoRestaurantAddress(foodOrderResponseDtoBuilder.fetchRestaurantAddressResponseDtoFromRestaurantManagement(foodOrder.getRestaurantAddressId())) 
							.foodOrderResponseDtoDeliveryPartnerName(foodOrderResponseDtoBuilder.fetchDeliveryPartnerNameFromDeliveryPartnerManagement(foodOrder.getDeliveryPartnerId()))
							.foodOrderResponseDtoDeliveryPartnerPhoneNumber(deliveryPartnerDetailsResponseDto.getDeliveryPartnerPhoneNumberResponseDto())
							.foodOrderResponseDtoDeliveryPartnerAssignmentId(foodOrder.getDeliveryAssignmentId())
							.foodOrderResponseDtoDeliveryPartnerAssignmentStatus(foodOrderResponseDtoBuilder.fetchDeliveryAssignmentFromOrderedFoodDetails(foodOrder.getDeliveryAssignmentId()))
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
	private String fetchOrderedUserNameFromUserManagement(long userId) {
		userInformationResponseDto = restTemplate.getForObject("http://localhost:9001/user/"+userId, UserInformationResponseDto.class);
		String userName = userInformationResponseDto.getUserFirstNameResponseDto() +" "+userInformationResponseDto.getUserLastNameResponseDto();
		return userName;
	}

//fetching details of Restaurant from RestaurantManagement Service related logic
	private String fetchRestaurantNameFromRestaurantManagement(long restaurantId) {
		restaurantDetailsResponseDto = restTemplate.getForObject("http://localhost:9002/restaurant/"+restaurantId, RestaurantDetailsResponseDto.class);
		String restaurantName = restaurantDetailsResponseDto.getRestaurantNameResponseDto();
		return restaurantName;
	}

//fetching details of DeliveryPartner from DeliveryPartnerManagement Service related logic
	private String fetchDeliveryPartnerNameFromDeliveryPartnerManagement(long deliveryPartnerId) {
		deliveryPartnerDetailsResponseDto = restTemplate.getForObject("http://localhost:9003/deliveryPartner/"+deliveryPartnerId, DeliveryPartnerDetailsResponseDto.class);
		String deliveryPartnerName = deliveryPartnerDetailsResponseDto.getDeliveryPartnerNameResponseDto();
		return deliveryPartnerName;
	}
		
//fetching Details of DeliveryAssignment from DeliveryPartnerManagement Service related logic
	private AssignmentStatus fetchDeliveryAssignmentFromOrderedFoodDetails(long deliveryPartnerAssignmentId) {
		deliveryPartnerAssignmentResponseDto = restTemplate.getForObject("http://localhost:9003/deliveryassignment/assign/find/"+deliveryPartnerAssignmentId, DeliveryPartnerAssignmentResponseDto.class);
		return deliveryPartnerAssignmentResponseDto.getDeliveryPartnerAssignmentResponseDtoAssignmentStatus(); 
	}
	
//fetching Details of User Address from UserManagement Service related logic
	private UserAddressInformationResponseDto fetchUserAddressInformationResponseDtoFromUserManagement(long userAddressId) {
		UserAddressInformationResponseDto userAddressInformationResponseDto = restTemplate.getForObject("http://localhost:9001/user/address/find/"+userAddressId, UserAddressInformationResponseDto.class);
		return userAddressInformationResponseDto;
	}
	
//fetching details of Restaurant Address from RestaurantManagement Service related logic
	private RestaurantAddressResponseDto fetchRestaurantAddressResponseDtoFromRestaurantManagement(long restaurantAddressId) {
		RestaurantAddressResponseDto restaurantAddressResponseDto = restTemplate.getForObject("http://localhost:9002/restaurant/address/find/"+restaurantAddressId, RestaurantAddressResponseDto.class);
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
