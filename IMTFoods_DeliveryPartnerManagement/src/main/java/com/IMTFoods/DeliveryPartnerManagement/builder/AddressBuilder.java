package com.IMTFoods.DeliveryPartnerManagement.builder;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.IMTFoods.DeliveryPartnerManagement.dto.DeliveryPartnerAddressResponseDto;
import com.IMTFoods.DeliveryPartnerManagement.model.DeliveryPartnerAddress;

@Component
public class AddressBuilder {
	
	public static List<DeliveryPartnerAddressResponseDto> buildListOfDeliveryPartnerAddressResponseDtoFromDeliveryPartnerAddress(List<DeliveryPartnerAddress> deliveryPartnerAddressList) {
		
		List<DeliveryPartnerAddressResponseDto> deliveryPartnerAddressResponseDtoList = new ArrayList<>();
		for(DeliveryPartnerAddress deliveryPartnerAddress : deliveryPartnerAddressList) {
			DeliveryPartnerAddressResponseDto deliveryPartnerAddressResponseDto = buildDeliveryPartnerAddressResponseDtoFromDeliveryPartnerAdress(deliveryPartnerAddress);
			deliveryPartnerAddressResponseDtoList.add(deliveryPartnerAddressResponseDto);
		}
		return deliveryPartnerAddressResponseDtoList;
	}

	public static DeliveryPartnerAddressResponseDto buildDeliveryPartnerAddressResponseDtoFromDeliveryPartnerAdress(
			DeliveryPartnerAddress deliveryPartnerAddress) {
		
		DeliveryPartnerAddressResponseDto deliveryPartnerAddressResponseDto = DeliveryPartnerAddressResponseDto.builder()
											.deliveryPartnerAddressHouseNumberResponseDto(deliveryPartnerAddress.getDeliveryPartnerAddressHouseNumber())
											.deliveryPartnerAddressStreetNameResponseDto(deliveryPartnerAddress.getDeliveryPartnerAddressStreetName())
											.deliveryPartnerAddressLandMarkNameResponseDto(deliveryPartnerAddress.getDeliveryPartnerAddressLandMarkName())
											.deliveryPartnerAddressDistrictNameResponseDto(deliveryPartnerAddress.getDeliveryPartnerAddressDistrictName())
											.deliveryPartnerAddressStateNameResponseDto(deliveryPartnerAddress.getDeliveryPartnerAddressStateName())
											.deliveryPartnerAddressCountryNameResponseDto(deliveryPartnerAddress.getDeliveryPartnerAddressCountryName())
											.deliveryPartnerAddressPinCodeResponseDto(deliveryPartnerAddress.getDeliveryPartnerAddressPinCode())
											.build();
		return deliveryPartnerAddressResponseDto;
	}

}
