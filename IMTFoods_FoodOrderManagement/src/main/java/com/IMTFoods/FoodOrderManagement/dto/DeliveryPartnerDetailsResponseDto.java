package com.IMTFoods.FoodOrderManagement.dto;

import com.IMTFoods.FoodOrderManagement.utils.CurrentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryPartnerDetailsResponseDto {

	private long deliveryPartnerId;
	
	private String deliveryPartnerNameResponseDto;
	
	private String deliveryPartnerAadharNumberResponseDto;
	
	private String deliveryPartnerEmailResponseDto;

	private LocalDate deliveryPartnerDateOfBirthResponseDto;
	
	private String deliveryPartnerPhoneNumberResponseDto;
	
	private CurrentStatus deliveryPartnerCurrentStatusResponseDto;
	
	private List<DeliveryPartnerAddressResponseDto> deliveryPartnerAddressResponseDto;
	
	private List<DeliveryPartnerAssignmentResponseDto> deliveryPartnerAssignmentsRequestDto;
	
	
}
