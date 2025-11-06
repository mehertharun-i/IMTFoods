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
public class UpdateUserInformationResponseDto {

	private String userFirstNameResponseDto;
	
	private String userLastNameResponseDto;
	
	private LocalDate userDateOfBirthResponseDto;
	
	private Gender genderResponseDto;
	
	private String userPhoneNumberResponseDto;
	
	private List<UserAddressInformationResponseDto> userAddressInformationResponseDto;
	
	private List<AuthoritiesResponseDto> rolesResponseDto;
	
}
