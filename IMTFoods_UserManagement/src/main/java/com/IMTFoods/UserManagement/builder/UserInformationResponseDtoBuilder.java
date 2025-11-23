package com.IMTFoods.UserManagement.builder;

import java.util.ArrayList;
import java.util.List;

import com.IMTFoods.UserManagement.dto.AuthoritiesResponseDto;
import com.IMTFoods.UserManagement.dto.UserAddressInformationResponseDto;
import com.IMTFoods.UserManagement.dto.UserInformationResponseDto;
import com.IMTFoods.UserManagement.model.Authorities;
import com.IMTFoods.UserManagement.model.UserAddressInformation;
import com.IMTFoods.UserManagement.model.UserInformation;

public class UserInformationResponseDtoBuilder {

	
	public static UserInformationResponseDto buildUserInformationResponseDtoFromUserInformation(UserInformation userInformation) {
		UserInformationResponseDto userInformationResponseDto = UserInformationResponseDto.builder()
																.userFirstNameResponseDto(userInformation.getUserFirstName())
																.userLastNameResponseDto(userInformation.getUserLastName())
																.userDateOfBirthResponseDto(userInformation.getUserDateOfBirth())
																.userPhoneNumberResponseDto(userInformation.getUserPhoneNumber())
																.userAddressResponseDto(buildUserAddressInformationResponseDtoFromUserInformation(userInformation.getUserAddressInformation()))
																.rolesResponseDto(buildAuthoritiesResponseDtoFromAuthorities(userInformation.getRoles()))
																.build();
		
		return userInformationResponseDto;
	}
	
	private static List<UserAddressInformationResponseDto> buildUserAddressInformationResponseDtoFromUserInformation(List<UserAddressInformation> userAddressInformationList) {
		List<UserAddressInformationResponseDto> userAddressInformationResponseDtoList = new ArrayList<>();
		
		for(UserAddressInformation userAddressInformation : userAddressInformationList) {
			UserAddressInformationResponseDto userAddressInformationResponseDto = new UserAddressInformationResponseDto();
			
			userAddressInformationResponseDto.setUserHouseNumberResponseDto(userAddressInformation.getUserHouseNumber());
			userAddressInformationResponseDto.setUserLandMarkResponseDto(userAddressInformation.getUserLandMark());
			userAddressInformationResponseDto.setUserStreetResponseDto(userAddressInformation.getUserStreet());
			userAddressInformationResponseDto.setUserDistrictResponseDto(userAddressInformation.getUserDistrict());
			userAddressInformationResponseDto.setUserStateResponseDto(userAddressInformation.getUserState());
			userAddressInformationResponseDto.setUserCountryResponseDto(userAddressInformation.getUserCountry());
			userAddressInformationResponseDto.setUserPincodeResponseDto(userAddressInformation.getUserPincode());
			
			userAddressInformationResponseDtoList.add(userAddressInformationResponseDto);
		}
		
		return userAddressInformationResponseDtoList;
	}
	
	private static List<AuthoritiesResponseDto> buildAuthoritiesResponseDtoFromAuthorities(List<Authorities> authoritiesList){
		
		List<AuthoritiesResponseDto> authoritiesResponseDtoList = new ArrayList<>();
		
		for(Authorities authority : authoritiesList) {
			AuthoritiesResponseDto authoritiesResponseDto = new AuthoritiesResponseDto();
			authoritiesResponseDto.setRolesResponseDto(authority.getRoles());
			authoritiesResponseDtoList.add(authoritiesResponseDto);
		}
		
		return authoritiesResponseDtoList;
	}
}
