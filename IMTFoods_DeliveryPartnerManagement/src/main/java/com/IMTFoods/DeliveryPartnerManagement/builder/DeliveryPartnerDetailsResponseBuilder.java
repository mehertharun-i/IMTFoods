package com.IMTFoods.DeliveryPartnerManagement.builder;

import java.util.ArrayList;
import java.util.List;

import com.IMTFoods.DeliveryPartnerManagement.dto.DeliveryPartnerAddressResponseDto;
import com.IMTFoods.DeliveryPartnerManagement.dto.DeliveryPartnerDetailsResponseDto;
import com.IMTFoods.DeliveryPartnerManagement.model.DeliveryPartnerAddress;
import com.IMTFoods.DeliveryPartnerManagement.model.DeliveryPartnerDetails;

public class DeliveryPartnerDetailsResponseBuilder {
	
	public static DeliveryPartnerDetailsResponseDto buildDeliveryPartnerDetailsResponseDtoFromDeliveryPartnerDetails(DeliveryPartnerDetails deliveryPartnerDetails) {
		
		DeliveryPartnerDetailsResponseDto deliveryPartnerDetailsResponseDto = DeliveryPartnerDetailsResponseDto.builder()
										.deliveryPartnerNameResponseDto(deliveryPartnerDetails.getDeliveryPartnerName())
										.deliveryPartnerAadharNumberResponseDto(deliveryPartnerDetails.getDeliveryPartnerAadharNumber())
										.deliveryPartnerEmailResponseDto(deliveryPartnerDetails.getDeliveryPartnerEmail())
										.deliveryPartnerDateOfBirthResponseDto(deliveryPartnerDetails.getDeliveryPartnerDateOfBirth())
										.deliveryPartnerPhoneNumberResponseDto(deliveryPartnerDetails.getDeliveryPartnerPhoneNumber())
										.deliveryPartnerCurrentStatusResponseDto(deliveryPartnerDetails.getDeliveryPartnerCurrentStatus())
										.deliveryPartnerAddressResponseDto(buildListOfDeliveryPartnerAddressResponseDtoFromDeliveryPartnerAddress(deliveryPartnerDetails.getDeliveryPartnerAddress()))
										.build();		
		return deliveryPartnerDetailsResponseDto;
	}

	private static List<DeliveryPartnerAddressResponseDto> buildListOfDeliveryPartnerAddressResponseDtoFromDeliveryPartnerAddress(List<DeliveryPartnerAddress> deliveryPartnerAddressList) {
		
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
