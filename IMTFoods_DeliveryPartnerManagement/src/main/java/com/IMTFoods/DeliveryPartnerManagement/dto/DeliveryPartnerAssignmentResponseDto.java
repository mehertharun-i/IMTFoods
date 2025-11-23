package com.IMTFoods.DeliveryPartnerManagement.dto;

import java.time.Instant;

import com.IMTFoods.DeliveryPartnerManagement.utils.AssignmentStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryPartnerAssignmentResponseDto {

	private long deliveryPartnerAssignmentResponseDtoAssignmentId;
	
	private AssignmentStatus deliveryPartnerAssignmentResponseDtoAssignmentStatus;
	
	private long deliveryPartnerAssignmentResponseDtoOrderId;
	
	private String deliveryPartnerAssignmentResponseDtoRestaurantName;
	
	private String deliveryPartnerAssignmentResponseDtoRestaurantPhoneNumber;
	
	private RestaurantAddressResponseDto deliveryPartnerAssignmentResponseDtoRestaurantAddress;
	
	private String deliveryPartnerAssignmentResponseDtoDeliveryUserName;
	
	private String deliveryPartnerAssignmentResponseDtoDeliveryUserPhoneNumber;
	
	private UserAddressInformationResponseDto deliveryPartnerAssignmentResponseDtoDeliveryUserAddress;
	
	private Instant deliveryPartnerAssignmentResponseDtoDeliveryAssignmentCreatedAt;
	
	private Instant deliveryPartnerAssignmentResponseDtoEstimatedDeliveryTime;
	
	
	
}
