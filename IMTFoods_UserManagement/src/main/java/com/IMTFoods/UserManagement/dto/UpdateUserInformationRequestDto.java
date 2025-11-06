package com.IMTFoods.UserManagement.dto;

import java.time.LocalDate;
import java.util.List;

import com.IMTFoods.UserManagement.utils.Gender;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserInformationRequestDto {

	private String userFirstNameReqeustDto;
	
	private String userLastNameReqeustDto;
	
	private LocalDate userDateOfBirthReqeustDto;
	
	private Gender genderReqeustDto;
	
	private String userPhoneNumberReqeustDto;
	
	private List<UserAddressInformationRequestDto> userAddressInformationReqeustDto;
	
	private List<AuthoritiesRequestDto> rolesRequestDto;
	
}
