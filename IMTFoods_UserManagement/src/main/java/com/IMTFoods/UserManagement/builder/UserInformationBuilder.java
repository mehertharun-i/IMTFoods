package com.IMTFoods.UserManagement.builder;

import java.util.ArrayList;
import java.util.List;

import com.IMTFoods.UserManagement.dto.AuthoritiesRequestDto;
import com.IMTFoods.UserManagement.dto.UserAddressInformationRequestDto;
import com.IMTFoods.UserManagement.dto.UserInformationRequestDto;
import com.IMTFoods.UserManagement.model.Authorities;
import com.IMTFoods.UserManagement.model.UserAddressInformation;
import com.IMTFoods.UserManagement.model.UserInformation;

public class UserInformationBuilder {
		
	public static UserInformation buildUserInformationFromUserInformationRequestDto(UserInformationRequestDto userInformationRequestDto) {
		UserInformation userInformation = UserInformation.builder()
														.userFirstName(userInformationRequestDto.getUserFirstNameRequestDto())
														.userLastName(userInformationRequestDto.getUserLastNameRequestDto())
														.userDateOfBirth(userInformationRequestDto.getUserDateOfBirthRequestDto())
														.gender(userInformationRequestDto.getGenderRequestDto())
														.userEmail(userInformationRequestDto.getUserEmailRequestDto())
														.userPassword(userInformationRequestDto.getUserPasswordRequestDto())
														.userPhoneNumber(userInformationRequestDto.getUserPhoneNumberRequestDto())
														.userAddressInformation(buildUserAddressInformationFromUserInformationRequestDto(userInformationRequestDto.getUserAddressInformationRequestDto()))
														.roles(buildAuthoritiesFromAuthoritiesRequestDto(userInformationRequestDto.getRolesRequestDto()))
														.build();
		
		return userInformation;
	}
	
	
	private static List<UserAddressInformation> buildUserAddressInformationFromUserInformationRequestDto(List<UserAddressInformationRequestDto> userAddressInformationRequestDtoList) {

		List<UserAddressInformation> userAddressInformationList = new ArrayList<>();
		
		for(UserAddressInformationRequestDto userAddressInformationRequestDto : userAddressInformationRequestDtoList) {
			UserAddressInformation userAddressInformation = new UserAddressInformation();
			userAddressInformation.setUserHouseNumber(userAddressInformationRequestDto.getUserHouseNumberRequestDto());
			userAddressInformation.setUserLandMark(userAddressInformationRequestDto.getUserLandMarkRequestDto());
			userAddressInformation.setUserStreet(userAddressInformationRequestDto.getUserStreetRequestDto());
			userAddressInformation.setUserDistrict(userAddressInformationRequestDto.getUserDistrictRequestDto());
			userAddressInformation.setUserState(userAddressInformationRequestDto.getUserStateRequestDto());
			userAddressInformation.setUserCountry(userAddressInformationRequestDto.getUserCountryRequestDto());
			userAddressInformation.setUserPincode(userAddressInformationRequestDto.getUserPincodeRequestDto());
			userAddressInformationList.add(userAddressInformation);
		}
		
		return userAddressInformationList;
	}
	
	private static List<Authorities> buildAuthoritiesFromAuthoritiesRequestDto(List<AuthoritiesRequestDto> authoritiesRequestDtoList){
		
		List<Authorities> authoritiesList = new ArrayList<>();
		
		for(AuthoritiesRequestDto authorities : authoritiesRequestDtoList) {
			Authorities authority = new Authorities();
			authority.setRoles(authorities.getRoleRequestDto());
			authoritiesList.add(authority);
		}
		
		return authoritiesList;
	}

}
