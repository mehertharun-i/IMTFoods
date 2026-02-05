package com.IMTFoods.FoodOrderManagement.dto;

import com.IMTFoods.FoodOrderManagement.utils.AssignmentStatus;
import com.IMTFoods.FoodOrderManagement.utils.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FoodOrderResponseDto {

	private long foodOrderResponseDtoOrderId;
	
	private long foodOrderResponseDtoTrackingId;
	
	private String foodOrderResponseDtoOrderedUserName;
	
	private String foodOrderResponseDtoOrderedUserPhoneNumber;
	
	private UserAddressInformationResponseDto foodOrderResponseDtoOrderedUserAddressDetails;
	
	private String foodOrderResponseDtoRestaurantName;

	private String foodOrderResponseDtoRestaurantPhoneNumber;
	
	private RestaurantAddressResponseDto foodOrderResponseDtoRestaurantAddress;
	
	private String foodOrderResponseDtoDeliveryPartnerName;

	private String foodOrderResponseDtoDeliveryPartnerPhoneNumber;
	
	private long foodOrderResponseDtoDeliveryPartnerAssignmentId;
	
	private AssignmentStatus foodOrderResponseDtoDeliveryPartnerAssignmentStatus;
	
	private Instant foodOrderResponseDtoDeliveryPartnerAssignmentCreatedAt;
	
	private Instant foodOrderResponseDtoEstimatedDeliveryTime;
	
	private List<OrderItemsResponseDto> foodOrderResponseDtoOrderItemsResponseDto;
	
	private double foodOrderResponseDtoOrderTotalPrice;
	
	private PaymentDetailsResponseDto foodOrderResponseDtoPaymentDetailsResponseDto;
	
	private OrderStatus foodOrderResponseDtoOrderStatus;
	
}
