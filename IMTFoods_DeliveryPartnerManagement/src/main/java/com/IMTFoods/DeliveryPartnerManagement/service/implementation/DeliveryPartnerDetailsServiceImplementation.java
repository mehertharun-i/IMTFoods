package com.IMTFoods.DeliveryPartnerManagement.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.IMTFoods.DeliveryPartnerManagement.builder.DeliveryPartnerDetailsBuilder;
import com.IMTFoods.DeliveryPartnerManagement.builder.DeliveryPartnerDetailsResponseBuilder;
import com.IMTFoods.DeliveryPartnerManagement.builder.DeliveryPartnerDetailsResponseListBuilder;
import com.IMTFoods.DeliveryPartnerManagement.builder.DeliveryPartnerDetailsUpdateResponseBuilder;
import com.IMTFoods.DeliveryPartnerManagement.dao.DeliveryPartnerRepository;
import com.IMTFoods.DeliveryPartnerManagement.dto.DeliveryPartnerAddressRequestDto;
import com.IMTFoods.DeliveryPartnerManagement.dto.DeliveryPartnerDetailsRequestDto;
import com.IMTFoods.DeliveryPartnerManagement.dto.DeliveryPartnerDetailsResponseDto;
import com.IMTFoods.DeliveryPartnerManagement.dto.DeliveryPartnerDetailsUpdateRequestDto;
import com.IMTFoods.DeliveryPartnerManagement.dto.DeliveryPartnerDetailsUpdateResponseDto;
import com.IMTFoods.DeliveryPartnerManagement.exception.DeliveryPartnerIdNotFoundException;
import com.IMTFoods.DeliveryPartnerManagement.model.DeliveryPartnerAddress;
import com.IMTFoods.DeliveryPartnerManagement.model.DeliveryPartnerDetails;
import com.IMTFoods.DeliveryPartnerManagement.service.DeliveryPartnerDetailsService;
import com.IMTFoods.DeliveryPartnerManagement.utils.CurrentStatus;

@Service
public class DeliveryPartnerDetailsServiceImplementation implements DeliveryPartnerDetailsService {

	private final DeliveryPartnerRepository deliveryPartnerRepository;
	
	public DeliveryPartnerDetailsServiceImplementation(DeliveryPartnerRepository deliveryPartnerRepository) {
		this.deliveryPartnerRepository = deliveryPartnerRepository;
	}
	
	@Override
	public DeliveryPartnerDetailsResponseDto signIn(
			DeliveryPartnerDetailsRequestDto deliveryPartnerDetailsRequestDto) {

		DeliveryPartnerDetails deliveryPartnerDetails = DeliveryPartnerDetailsBuilder.buildDeliveryPartnerDetailsFromDeliveryPartnerDetailsRequestDto(deliveryPartnerDetailsRequestDto);
		DeliveryPartnerDetails savedDeliveryPartnerDetails = deliveryPartnerRepository.save(deliveryPartnerDetails);
		DeliveryPartnerDetailsResponseDto deliveryPartnerDetailsResponseDto = DeliveryPartnerDetailsResponseBuilder.buildDeliveryPartnerDetailsResponseDtoFromDeliveryPartnerDetails(savedDeliveryPartnerDetails);
		return deliveryPartnerDetailsResponseDto;
	}

	@Override
	public DeliveryPartnerDetailsResponseDto getDeliveryPartnerDetailsById(long deliveryPartnerId) {
		DeliveryPartnerDetails deliveryPartnerDetails = deliveryPartnerRepository.findById(deliveryPartnerId).orElseThrow( () -> new DeliveryPartnerIdNotFoundException("Invalid Delivery Partner Id"));
		DeliveryPartnerDetailsResponseDto deliveryPartnerDetailsResponseDto = DeliveryPartnerDetailsResponseBuilder.buildDeliveryPartnerDetailsResponseDtoFromDeliveryPartnerDetails(deliveryPartnerDetails);
		return deliveryPartnerDetailsResponseDto;
	}

	@Override
	public List<DeliveryPartnerDetailsResponseDto> getAllDeliveryPartnerDetails() {
		List<DeliveryPartnerDetails> allDeliveryPartnerDetails = deliveryPartnerRepository.findAll();
		List<DeliveryPartnerDetailsResponseDto> listOfDeliveryPartnerDetailsResponseDto = DeliveryPartnerDetailsResponseListBuilder.buildListOfDeliveryPartnerDetailsResponseDtoFromListOfDeliveryPartnerDetails(allDeliveryPartnerDetails);
		return listOfDeliveryPartnerDetailsResponseDto;
	}

	@Override
	public void deleteDeliveryPartnerById(long deliveryPartnerId) {
		DeliveryPartnerDetails deliveryPartnerDetails = deliveryPartnerRepository.findById(deliveryPartnerId).orElseThrow( () -> new DeliveryPartnerIdNotFoundException("Invalid Delivery Partner Id"));
		deliveryPartnerRepository.deleteById(deliveryPartnerDetails.getDeliveryPartnerId());
	}

	@Override
	public DeliveryPartnerDetailsUpdateResponseDto updateDeliveryPartnerDetailsById(long deliveryPartnerId,
			DeliveryPartnerDetailsUpdateRequestDto deliveryPartnerDetailsUpdateRequestDto) {
		
		DeliveryPartnerDetails deliveryPartnerDetails = deliveryPartnerRepository.findById(deliveryPartnerId).orElseThrow( () -> new DeliveryPartnerIdNotFoundException("Invalid Delivery Partner Id"));
		
		deliveryPartnerDetails.setDeliveryPartnerName(deliveryPartnerDetailsUpdateRequestDto.getDeliveryPartnerNameRequestDto());
		deliveryPartnerDetails.setDeliveryPartnerAadharNumber(deliveryPartnerDetailsUpdateRequestDto.getDeliveryPartnerAadharNumberRequestDto());
		deliveryPartnerDetails.setDeliveryPartnerEmail(deliveryPartnerDetailsUpdateRequestDto.getDeliveryPartnerEmailRequestDto());
		deliveryPartnerDetails.setDeliveryPartnerDateOfBirth(deliveryPartnerDetailsUpdateRequestDto.getDeliveryPartnerDateOfBirthRequestDto());
		deliveryPartnerDetails.setDeliveryPartnerPhoneNumber(deliveryPartnerDetailsUpdateRequestDto.getDeliveryPartnerPhoneNumberRequestDto());
		deliveryPartnerDetails.setDeliveryPartnerCurrentStatus(deliveryPartnerDetailsUpdateRequestDto.getDeliveryPartnerCurrentStatusRequestDto());
		deliveryPartnerDetails.setIsloggedIn(deliveryPartnerDetailsUpdateRequestDto.getDeliveryPartnerCurrentStatusRequestDto().equals(CurrentStatus.OFFLINE) ? false : true);

		List<DeliveryPartnerAddress> deliveryPartnerAddressList = new ArrayList<>();
		int count = 0;
		
		for(DeliveryPartnerAddressRequestDto deliveryPartnerAddressRequestDto : deliveryPartnerDetailsUpdateRequestDto.getDeliveryPartnerAddressRequestDto()) {
			DeliveryPartnerAddress deliveryPartnerAddress = DeliveryPartnerDetailsBuilder.buildDeliveryPartnerAddressFromDeliveryPartnerAddressRequestDto(deliveryPartnerAddressRequestDto);
			deliveryPartnerAddress.setDeliveryPartnerAddressId(deliveryPartnerDetails.getDeliveryPartnerAddress().get(count++).getDeliveryPartnerAddressId());
			deliveryPartnerAddress.setDeliveryPartnerDetailsId(deliveryPartnerDetails);
			deliveryPartnerAddressList.add(deliveryPartnerAddress);
		}
		deliveryPartnerDetails.getDeliveryPartnerAddress().clear();
		deliveryPartnerDetails.getDeliveryPartnerAddress().addAll(deliveryPartnerAddressList);
		
		DeliveryPartnerDetails updatedDeliveryPartnerDetails = deliveryPartnerRepository.save(deliveryPartnerDetails);
		DeliveryPartnerDetailsUpdateResponseDto deliveryPartnerDetailsUpdateResponseDto = DeliveryPartnerDetailsUpdateResponseBuilder.buildDeliveryPartnerDetailsUpdateResponseDtoFromUpdatedDeliveryPartnerDetails(updatedDeliveryPartnerDetails);
		
		return deliveryPartnerDetailsUpdateResponseDto;
	}

	@Override
	public Long getAvailableDeliveryPartnerDetails() {

		List<DeliveryPartnerDetails> allDeliveryPartnerDetails = deliveryPartnerRepository.findAll();
		
		long availableDeliveryPartnerId = 0;
		List<Long> availableDeliveryPartnerIdList = new ArrayList<>();
		
		for(DeliveryPartnerDetails deliveryPartnerDetails : allDeliveryPartnerDetails) {
			if(deliveryPartnerDetails.getDeliveryPartnerCurrentStatus().equals(CurrentStatus.ONLINE)) {
				availableDeliveryPartnerIdList.add(deliveryPartnerDetails.getDeliveryPartnerId());
			}
		}
		
		Random random = new Random();
		
		long generatesRandomDeliveryPartnerIndex = Math.abs(random.nextLong(availableDeliveryPartnerIdList.size()));
		
		availableDeliveryPartnerId = availableDeliveryPartnerIdList.get((int)generatesRandomDeliveryPartnerIndex);
		
		return availableDeliveryPartnerId;
	}

	

}
