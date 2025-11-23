package com.IMTFoods.UserManagement.builder;

import com.IMTFoods.UserManagement.dto.UserAddressInformationResponseDto;
import com.IMTFoods.UserManagement.model.UserAddressInformation;

public class UserAddressInformationResponseDtoBuilder {
	
	public static UserAddressInformationResponseDto buildUserAddressInformationResponseDtoFromUserAddressInformation(UserAddressInformation userAddressInformation) {
		UserAddressInformationResponseDto userAddressInformationResponseDto = UserAddressInformationResponseDto.builder()
										.userHouseNumberResponseDto(userAddressInformation.getUserHouseNumber())
										.userLandMarkResponseDto(userAddressInformation.getUserLandMark())
										.userStreetResponseDto(userAddressInformation.getUserStreet())
										.userDistrictResponseDto(userAddressInformation.getUserDistrict())
										.userStateResponseDto(userAddressInformation.getUserState())
										.userCountryResponseDto(userAddressInformation.getUserCountry())
										.userPincodeResponseDto(userAddressInformation.getUserPincode())
										.build();
		return userAddressInformationResponseDto;

	}

}
