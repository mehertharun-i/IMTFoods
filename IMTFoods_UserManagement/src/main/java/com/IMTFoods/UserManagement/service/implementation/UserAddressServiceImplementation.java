package com.IMTFoods.UserManagement.service.implementation;

import org.springframework.stereotype.Service;

import com.IMTFoods.UserManagement.builder.UserAddressInformationResponseDtoBuilder;
import com.IMTFoods.UserManagement.dao.UserAddressInformationRepository;
import com.IMTFoods.UserManagement.dto.UserAddressInformationResponseDto;
import com.IMTFoods.UserManagement.exception.UserAddressIdNotFoundException;
import com.IMTFoods.UserManagement.model.UserAddressInformation;
import com.IMTFoods.UserManagement.service.UserAddressService;

@Service
public class UserAddressServiceImplementation implements UserAddressService{
	
	private final UserAddressInformationRepository userAddressInformationRepository;
	
	public UserAddressServiceImplementation(UserAddressInformationRepository userAddressInformationRepository) {
		this.userAddressInformationRepository = userAddressInformationRepository;
	}

	@Override
	public UserAddressInformationResponseDto getUserAddressInformationById(long userAddressId) {
		UserAddressInformation userAddressInformation = userAddressInformationRepository.findById(userAddressId).orElseThrow( () -> new UserAddressIdNotFoundException("Invalid User Address Id"));
		UserAddressInformationResponseDto userAddressInformationResponseDto = UserAddressInformationResponseDtoBuilder.buildUserAddressInformationResponseDtoFromUserAddressInformation(userAddressInformation);
		return userAddressInformationResponseDto;
	}

}
