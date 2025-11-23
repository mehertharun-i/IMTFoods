package com.IMTFoods.DeliveryPartnerManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryPartnerAddressResponseDto {

	private String deliveryPartnerAddressHouseNumberResponseDto;
	
	private String deliveryPartnerAddressStreetNameResponseDto;
	
	private String deliveryPartnerAddressLandMarkNameResponseDto;
	
	private String deliveryPartnerAddressDistrictNameResponseDto;
	
	private String deliveryPartnerAddressStateNameResponseDto;
	
	private String deliveryPartnerAddressCountryNameResponseDto;
	
	private int deliveryPartnerAddressPinCodeResponseDto;
	
}
