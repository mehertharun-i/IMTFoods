package com.IMTFoods.DeliveryPartnerManagement.builder;

import java.time.Instant;

import com.IMTFoods.DeliveryPartnerManagement.dto.DeliveryPartnerAssignmentRequestDto;
import com.IMTFoods.DeliveryPartnerManagement.model.DeliveryPartnerAssignments;
import com.IMTFoods.DeliveryPartnerManagement.utils.AssignmentStatus;

public class DeliveryPartnerAssignmentBuilder {
	
	
	public static DeliveryPartnerAssignments buildDeliveryPartnerAssignmentFromDeliveryPartnerAssignmentRequestDto(DeliveryPartnerAssignmentRequestDto deliveryPartnerAssignmentRequestDto) {
		
		DeliveryPartnerAssignments deliveryPartnerAssignments = DeliveryPartnerAssignments.builder()
									.currentDistanceDeliveryInKms(Math.random()*40) 
									.assignmentStatus(AssignmentStatus.ACCEPTED)
									.orderId(deliveryPartnerAssignmentRequestDto.getDeliveryPartnerAssignmentRequestDtoOrderId())
									.currentRestaurantId(deliveryPartnerAssignmentRequestDto.getDeliveryPartnerAssignmentRequestDtorestaurantId())
									.currentRestaurantAddressId(deliveryPartnerAssignmentRequestDto.getDeliveryPartnerAssignmentRequestDtoRestaurantAddressId())
									.currentOrderedUserId(deliveryPartnerAssignmentRequestDto.getDeliveryPartnerAssignmentRequestDtoUserId())
									.currentOrderedUserAddressId(deliveryPartnerAssignmentRequestDto.getDeliveryPartnerAssignmentRequestDtoUserAddressId())
									.estimatedDeliveryTime(Instant.now().plusSeconds(Math.round(Math.random()*1800)))
									.build();
		return deliveryPartnerAssignments;
		
	}
	
	

}
