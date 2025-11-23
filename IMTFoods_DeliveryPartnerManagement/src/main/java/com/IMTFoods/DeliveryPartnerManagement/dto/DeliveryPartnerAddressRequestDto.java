package com.IMTFoods.DeliveryPartnerManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeliveryPartnerAddressRequestDto {

	private String deliveryPartnerAddressHouseNumberRequestDto;
	
	private String deliveryPartnerAddressStreetNameRequestDto;
	
	private String deliveryPartnerAddressLandMarkNameRequestDto;
	
	private String deliveryPartnerAddressDistrictNameRequestDto;
	
	private String deliveryPartnerAddressStateNameRequestDto;
	
	private String deliveryPartnerAddressCountryNameRequestDto;
	
	private int deliveryPartnerAddressPinCodeRequestDto;

	
}
