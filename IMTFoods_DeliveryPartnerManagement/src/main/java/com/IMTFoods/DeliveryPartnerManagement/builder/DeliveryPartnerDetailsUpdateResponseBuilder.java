package com.IMTFoods.DeliveryPartnerManagement.builder;

import org.springframework.stereotype.Component;

import com.IMTFoods.DeliveryPartnerManagement.dto.DeliveryPartnerDetailsUpdateResponseDto;
import com.IMTFoods.DeliveryPartnerManagement.model.DeliveryPartnerDetails;

@Component
public class DeliveryPartnerDetailsUpdateResponseBuilder {
	
	public static DeliveryPartnerDetailsUpdateResponseDto buildDeliveryPartnerDetailsUpdateResponseDtoFromUpdatedDeliveryPartnerDetails(DeliveryPartnerDetails deliveryPartnerDetails) {
		
		DeliveryPartnerDetailsUpdateResponseDto deliveryPartnerDetailsUpdateResponseDto = DeliveryPartnerDetailsUpdateResponseDto.builder()
										.deliveryPartnerNameResponseDto(deliveryPartnerDetails.getDeliveryPartnerName())
										.deliveryPartnerAadharNumberResponseDto(deliveryPartnerDetails.getDeliveryPartnerAadharNumber())
										.deliveryPartnerEmailResponseDto(deliveryPartnerDetails.getDeliveryPartnerEmail())
										.deliveryPartnerDateOfBirthResponseDto(deliveryPartnerDetails.getDeliveryPartnerDateOfBirth())
										.deliveryPartnerPhoneNumberResponseDto(deliveryPartnerDetails.getDeliveryPartnerPhoneNumber())
										.deliveryPartnerCurrentStatusResponseDto(deliveryPartnerDetails.getDeliveryPartnerCurrentStatus())
										.deliveryPartnerAddressResponseDto(AddressBuilder.buildListOfDeliveryPartnerAddressResponseDtoFromDeliveryPartnerAddress(deliveryPartnerDetails.getDeliveryPartnerAddress()))
										.build();		
		return deliveryPartnerDetailsUpdateResponseDto;
	}

}
