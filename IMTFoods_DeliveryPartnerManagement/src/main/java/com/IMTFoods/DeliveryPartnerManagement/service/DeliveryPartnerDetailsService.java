package com.IMTFoods.DeliveryPartnerManagement.service;

import java.util.List;

import com.IMTFoods.DeliveryPartnerManagement.dto.DeliveryPartnerDetailsRequestDto;
import com.IMTFoods.DeliveryPartnerManagement.dto.DeliveryPartnerDetailsResponseDto;
import com.IMTFoods.DeliveryPartnerManagement.dto.DeliveryPartnerDetailsUpdateRequestDto;
import com.IMTFoods.DeliveryPartnerManagement.dto.DeliveryPartnerDetailsUpdateResponseDto;

public interface DeliveryPartnerDetailsService {

	DeliveryPartnerDetailsResponseDto signIn(DeliveryPartnerDetailsRequestDto deliveryPartnerDetailsRequestDto);

	DeliveryPartnerDetailsResponseDto getDeliveryPartnerDetailsById(long deliveryPartnerId);

	List<DeliveryPartnerDetailsResponseDto> getAllDeliveryPartnerDetails();

	void deleteDeliveryPartnerById(long deliveryPartnerId);

	DeliveryPartnerDetailsUpdateResponseDto updateDeliveryPartnerDetailsById(long deliveryPartnerId,
			DeliveryPartnerDetailsUpdateRequestDto deliveryPartnerDetailsUpdateRequestDto);

	Long getAvailableDeliveryPartnerDetails();
	
	
}
