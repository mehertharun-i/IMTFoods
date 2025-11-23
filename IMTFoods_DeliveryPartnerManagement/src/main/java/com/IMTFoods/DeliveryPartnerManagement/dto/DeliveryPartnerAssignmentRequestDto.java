package com.IMTFoods.DeliveryPartnerManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder	
public class DeliveryPartnerAssignmentRequestDto {
	
	private long deliveryPartnerAssignmentRequestDtoOrderId;
	
	private long deliveryPartnerAssignmentRequestDtoUserId;
	
	private long deliveryPartnerAssignmentRequestDtoUserAddressId;
	
	private long deliveryPartnerAssignmentRequestDtorestaurantId;
	
	private long deliveryPartnerAssignmentRequestDtoRestaurantAddressId;
	
	private long deliveryPartnerAssignmentRequestDtoDeliveryPartnerId;

}
