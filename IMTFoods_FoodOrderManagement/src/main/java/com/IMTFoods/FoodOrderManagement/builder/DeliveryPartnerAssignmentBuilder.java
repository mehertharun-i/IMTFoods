package com.IMTFoods.FoodOrderManagement.builder;

import com.IMTFoods.FoodOrderManagement.dto.DeliveryPartnerAssignmentRequestDto;
import com.IMTFoods.FoodOrderManagement.model.FoodOrder;

public class DeliveryPartnerAssignmentBuilder {
	
	public static DeliveryPartnerAssignmentRequestDto buildDeliveryPartnerAssignmentRequestDtoFromFoodOrder(FoodOrder foodOrder) {
		
		DeliveryPartnerAssignmentRequestDto deliveryPartnerAssignmentRequestDto = DeliveryPartnerAssignmentRequestDto.builder()
										.deliveryPartnerAssignmentRequestDtoOrderId(foodOrder.getOrderId())
										.deliveryPartnerAssignmentRequestDtoUserId(foodOrder.getUserId())
										.deliveryPartnerAssignmentRequestDtoUserAddressId(foodOrder.getUserAddressId())
										.deliveryPartnerAssignmentRequestDtorestaurantId(foodOrder.getRestaurantId())
										.deliveryPartnerAssignmentRequestDtoRestaurantAddressId(foodOrder.getRestaurantAddressId())
										.deliveryPartnerAssignmentRequestDtoDeliveryPartnerId(foodOrder.getDeliveryPartnerId())
										.build();
		
		return deliveryPartnerAssignmentRequestDto;
	}

}
