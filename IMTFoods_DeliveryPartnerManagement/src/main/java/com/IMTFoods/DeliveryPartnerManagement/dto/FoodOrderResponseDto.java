package com.IMTFoods.DeliveryPartnerManagement.dto;

import java.util.List;

import com.IMTFoods.DeliveryPartnerManagement.utils.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FoodOrderResponseDto {

	private long foodOrderResponseDtoOrderId;
	
	private long foodOrderResponseDtoTrackingId;
	
	private String foodOrderResponseDtoOrderedUserName;
	
	private String foodOrderRepsonseDtoOrderedUserPhoneNumber;
	
	private UserAddressInformationResponseDto foodOrderResponseDtoOrderedUserAddressDetails;
	
	private String foodOrderResponseDtoRestaurantName;
	
	private RestaurantAddressResponseDto foodOrderRepsonseDtoRestaurantAddress;
	
	private String foodOrderResponseDtoDeliveryPartnerName;
	
	private String foodOrderResponseDtoDeliveryPartnerPhoneNumber;
		
	private List<OrderItemsResponseDto> foodOrderResponseDtoOrderItemsResponseDto;
	
	private double foodOrderResponseDtoOrderTotalPrice;
	
	private PaymentDetailsResponseDto foodOrderResponseDtoPaymentDetailsResponseDto;
	
	private OrderStatus foodOrderResponseDtoOrderStatus;
	
	
}
