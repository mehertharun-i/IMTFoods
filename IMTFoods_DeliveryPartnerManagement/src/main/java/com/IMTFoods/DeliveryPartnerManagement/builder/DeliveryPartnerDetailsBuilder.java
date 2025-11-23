package com.IMTFoods.DeliveryPartnerManagement.builder;

import java.util.ArrayList;
import java.util.List;

import com.IMTFoods.DeliveryPartnerManagement.dto.DeliveryPartnerAddressRequestDto;
import com.IMTFoods.DeliveryPartnerManagement.dto.DeliveryPartnerDetailsRequestDto;
import com.IMTFoods.DeliveryPartnerManagement.model.DeliveryPartnerAddress;
import com.IMTFoods.DeliveryPartnerManagement.model.DeliveryPartnerDetails;
import com.IMTFoods.DeliveryPartnerManagement.utils.CurrentStatus;

public class DeliveryPartnerDetailsBuilder {
	
	public static DeliveryPartnerDetails buildDeliveryPartnerDetailsFromDeliveryPartnerDetailsRequestDto(DeliveryPartnerDetailsRequestDto deliveryPartnerDetailsRequestDto) {
		DeliveryPartnerDetails deliveryPartnerDetails = DeliveryPartnerDetails.builder()
								.deliveryPartnerName(deliveryPartnerDetailsRequestDto.getDeliveryPartnerNameRequestDto())
								.deliveryPartnerAadharNumber(deliveryPartnerDetailsRequestDto.getDeliveryPartnerAadharNumberRequestDto())
								.deliveryPartnerEmail(deliveryPartnerDetailsRequestDto.getDeliveryPartnerEmailRequestDto())
								.deliveryPartnerPassword(deliveryPartnerDetailsRequestDto.getDeliveryPartnerPasswordRequestDto())
								.deliveryPartnerDateOfBirth(deliveryPartnerDetailsRequestDto.getDeliveryPartnerDateOfBirthRequestDto())
								.deliveryPartnerPhoneNumber(deliveryPartnerDetailsRequestDto.getDeliveryPartnerPhoneNumberRequestDto())
								.deliveryPartnerCurrentStatus(deliveryPartnerDetailsRequestDto.getDeliveryPartnerCurrentStatusRequestDto())
								.deliveryPartnerAddress(buildListOfDeliveryPartnerAddressFromDeliveryPartnerAddressRequestDto(deliveryPartnerDetailsRequestDto.getDeliveryPartnerAddressRequestDto()))
								.build();
		
		deliveryPartnerDetails.setIsloggedIn(deliveryPartnerDetails.getDeliveryPartnerCurrentStatus().equals(CurrentStatus.OFFLINE) ? false : true);
		linkDeliveryPartnerAddressToDeliveryPartnerDetails(deliveryPartnerDetails);
		
		return deliveryPartnerDetails;		
	}

	private static void linkDeliveryPartnerAddressToDeliveryPartnerDetails(
			DeliveryPartnerDetails deliveryPartnerDetails) {

		if(deliveryPartnerDetails.getDeliveryPartnerAddress() != null) {
			for(DeliveryPartnerAddress deliveryPartner : deliveryPartnerDetails.getDeliveryPartnerAddress()) {
				deliveryPartner.setDeliveryPartnerDetailsId(deliveryPartnerDetails);
			}
		}
		
	}

	private static List<DeliveryPartnerAddress> buildListOfDeliveryPartnerAddressFromDeliveryPartnerAddressRequestDto(List<DeliveryPartnerAddressRequestDto> deliveryPartnerAddressRequestDtoList) {
		
		List<DeliveryPartnerAddress> deliveryPartnerAddressList = new ArrayList<>();
		for(DeliveryPartnerAddressRequestDto deliveryPartnerAddressRequestDto : deliveryPartnerAddressRequestDtoList) {
			DeliveryPartnerAddress deliveryPartnerAddress = buildDeliveryPartnerAddressFromDeliveryPartnerAddressRequestDto(deliveryPartnerAddressRequestDto);
			deliveryPartnerAddressList.add(deliveryPartnerAddress);
		}
		
		return deliveryPartnerAddressList;
	}
	
	public static DeliveryPartnerAddress buildDeliveryPartnerAddressFromDeliveryPartnerAddressRequestDto(DeliveryPartnerAddressRequestDto deliveryPartnerAddressRequestDto) {
		
		DeliveryPartnerAddress deliveryPartnerAddress = DeliveryPartnerAddress.builder()
								.deliveryPartnerAddressHouseNumber(deliveryPartnerAddressRequestDto.getDeliveryPartnerAddressHouseNumberRequestDto())
								.deliveryPartnerAddressStreetName(deliveryPartnerAddressRequestDto.getDeliveryPartnerAddressStreetNameRequestDto())
								.deliveryPartnerAddressLandMarkName(deliveryPartnerAddressRequestDto.getDeliveryPartnerAddressLandMarkNameRequestDto())
								.deliveryPartnerAddressDistrictName(deliveryPartnerAddressRequestDto.getDeliveryPartnerAddressDistrictNameRequestDto())
								.deliveryPartnerAddressStateName(deliveryPartnerAddressRequestDto.getDeliveryPartnerAddressStateNameRequestDto())
								.deliveryPartnerAddressCountryName(deliveryPartnerAddressRequestDto.getDeliveryPartnerAddressCountryNameRequestDto())
								.deliveryPartnerAddressPinCode(deliveryPartnerAddressRequestDto.getDeliveryPartnerAddressPinCodeRequestDto())
								.build();
		
		return deliveryPartnerAddress;
	}
}
