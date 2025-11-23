package com.IMTFoods.DeliveryPartnerManagement.dto;

import java.time.LocalDate;
import java.util.List;

import com.IMTFoods.DeliveryPartnerManagement.utils.CurrentStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryPartnerDetailsUpdateResponseDto {

	private String deliveryPartnerNameResponseDto;
	
	private String deliveryPartnerAadharNumberResponseDto;
	
	private String deliveryPartnerEmailResponseDto;
		
	private LocalDate deliveryPartnerDateOfBirthResponseDto;
	
	private String deliveryPartnerPhoneNumberResponseDto;
	
	private CurrentStatus deliveryPartnerCurrentStatusResponseDto;
	
	private List<DeliveryPartnerAddressResponseDto> deliveryPartnerAddressResponseDto;

}
