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
public class DeliveryPartnerDetailsUpdateRequestDto {

	private String deliveryPartnerNameRequestDto;
	
	private String deliveryPartnerAadharNumberRequestDto;
	
	private String deliveryPartnerEmailRequestDto;
		
	private LocalDate deliveryPartnerDateOfBirthRequestDto;
	
	private String deliveryPartnerPhoneNumberRequestDto;
	
	private CurrentStatus deliveryPartnerCurrentStatusRequestDto;
	
	private List<DeliveryPartnerAddressRequestDto> deliveryPartnerAddressRequestDto;
	
}
