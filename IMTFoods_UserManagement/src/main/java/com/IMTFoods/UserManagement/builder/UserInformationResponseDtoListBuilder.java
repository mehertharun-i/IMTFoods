package com.IMTFoods.UserManagement.builder;

import java.util.ArrayList;
import java.util.List;

import com.IMTFoods.UserManagement.dto.UserInformationResponseDto;
import com.IMTFoods.UserManagement.model.UserInformation;

public class UserInformationResponseDtoListBuilder {
	
	public static List<UserInformationResponseDto> buildListOfUserInformationResponseDtoFromUserInformation(List<UserInformation> allUserList) {
		
		List<UserInformationResponseDto> userInformationResponseDtoList = new ArrayList<>();
		for(UserInformation userInformation : allUserList) {
			UserInformationResponseDto userInformationResponseDtoFromUserInformation = UserInformationResponseDtoBuilder.buildUserInformationResponseDtoFromUserInformation(userInformation);
			userInformationResponseDtoList.add(userInformationResponseDtoFromUserInformation);
			
		}
		
		return userInformationResponseDtoList;
	}

}
