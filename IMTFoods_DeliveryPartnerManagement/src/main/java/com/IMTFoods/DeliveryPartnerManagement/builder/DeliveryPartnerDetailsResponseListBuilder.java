package com.IMTFoods.DeliveryPartnerManagement.builder;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.IMTFoods.DeliveryPartnerManagement.dto.DeliveryPartnerDetailsResponseDto;
import com.IMTFoods.DeliveryPartnerManagement.model.DeliveryPartnerDetails;

@Component
public class DeliveryPartnerDetailsResponseListBuilder {

	public static List<DeliveryPartnerDetailsResponseDto> buildListOfDeliveryPartnerDetailsResponseDtoFromListOfDeliveryPartnerDetails(
			List<DeliveryPartnerDetails> allDeliveryPartnerDetails) {
		
		List<DeliveryPartnerDetailsResponseDto> deliveryPartnerDetailsResponseDtoList = new ArrayList<>();
		for(DeliveryPartnerDetails deliveryPartnerDetails : allDeliveryPartnerDetails) {
			DeliveryPartnerDetailsResponseDto deliveryPartnerDetailsResponseDto = DeliveryPartnerDetailsResponseBuilder.buildDeliveryPartnerDetailsResponseDtoFromDeliveryPartnerDetails(deliveryPartnerDetails);
			deliveryPartnerDetailsResponseDtoList.add(deliveryPartnerDetailsResponseDto);
		}
		return deliveryPartnerDetailsResponseDtoList;
	}

}
