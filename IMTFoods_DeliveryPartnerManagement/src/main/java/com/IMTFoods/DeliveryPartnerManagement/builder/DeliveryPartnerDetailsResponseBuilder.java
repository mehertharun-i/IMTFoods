package com.IMTFoods.DeliveryPartnerManagement.builder;

import org.springframework.stereotype.Component;

import com.IMTFoods.DeliveryPartnerManagement.dto.DeliveryPartnerDetailsResponseDto;
import com.IMTFoods.DeliveryPartnerManagement.model.DeliveryPartnerDetails;

@Component
public class DeliveryPartnerDetailsResponseBuilder {
	
	public static DeliveryPartnerDetailsResponseDto buildDeliveryPartnerDetailsResponseDtoFromDeliveryPartnerDetails(DeliveryPartnerDetails deliveryPartnerDetails) {
		
		DeliveryPartnerDetailsResponseDto deliveryPartnerDetailsResponseDto = DeliveryPartnerDetailsResponseDto.builder()
										.deliveryPartnerId(deliveryPartnerDetails.getDeliveryPartnerId())
										.deliveryPartnerNameResponseDto(deliveryPartnerDetails.getDeliveryPartnerName())
										.deliveryPartnerAadharNumberResponseDto(deliveryPartnerDetails.getDeliveryPartnerAadharNumber())
										.deliveryPartnerEmailResponseDto(deliveryPartnerDetails.getDeliveryPartnerEmail())
										.deliveryPartnerDateOfBirthResponseDto(deliveryPartnerDetails.getDeliveryPartnerDateOfBirth())
										.deliveryPartnerPhoneNumberResponseDto(deliveryPartnerDetails.getDeliveryPartnerPhoneNumber())
										.deliveryPartnerCurrentStatusResponseDto(deliveryPartnerDetails.getDeliveryPartnerCurrentStatus())
										.deliveryPartnerAddressResponseDto(AddressBuilder.buildListOfDeliveryPartnerAddressResponseDtoFromDeliveryPartnerAddress(deliveryPartnerDetails.getDeliveryPartnerAddress()))
										.build();		
		return deliveryPartnerDetailsResponseDto;
	}

}
