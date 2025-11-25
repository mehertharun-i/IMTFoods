package com.IMTFoods.DeliveryPartnerManagement.builder;

import java.time.Instant;
import java.util.Random;

import org.springframework.stereotype.Component;

import com.IMTFoods.DeliveryPartnerManagement.dto.DeliveryPartnerAssignmentRequestDto;
import com.IMTFoods.DeliveryPartnerManagement.model.DeliveryPartnerAssignments;
import com.IMTFoods.DeliveryPartnerManagement.utils.AssignmentStatus;

@Component
public class DeliveryPartnerAssignmentBuilder {
	
	private static final int MAX_DISTANCE_TO_BE_DELIVERIED_IN_KM = 40;
	private static final int MAX_ESTIMATED_DELIVERY_TIME = 1800;
	
	
	public static DeliveryPartnerAssignments buildDeliveryPartnerAssignmentFromDeliveryPartnerAssignmentRequestDto(DeliveryPartnerAssignmentRequestDto deliveryPartnerAssignmentRequestDto) {
		
		Random random = new Random();
		DeliveryPartnerAssignments deliveryPartnerAssignments = DeliveryPartnerAssignments.builder()
									.currentDistanceDeliveryInKms(Math.round(random.nextDouble()*MAX_DISTANCE_TO_BE_DELIVERIED_IN_KM)) 
									.assignmentStatus(AssignmentStatus.ACCEPTED)
									.orderId(deliveryPartnerAssignmentRequestDto.getDeliveryPartnerAssignmentRequestDtoOrderId())
									.currentRestaurantId(deliveryPartnerAssignmentRequestDto.getDeliveryPartnerAssignmentRequestDtorestaurantId())
									.currentRestaurantAddressId(deliveryPartnerAssignmentRequestDto.getDeliveryPartnerAssignmentRequestDtoRestaurantAddressId())
									.currentOrderedUserId(deliveryPartnerAssignmentRequestDto.getDeliveryPartnerAssignmentRequestDtoUserId())
									.currentOrderedUserAddressId(deliveryPartnerAssignmentRequestDto.getDeliveryPartnerAssignmentRequestDtoUserAddressId())
									.estimatedDeliveryTime(Instant.now().plusSeconds(Math.round(random.nextDouble()*MAX_ESTIMATED_DELIVERY_TIME)))
									.build();
		return deliveryPartnerAssignments;
		
	}
	
	

}
