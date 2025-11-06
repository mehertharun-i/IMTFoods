package com.IMTFoods.UserManagement.dto;

import java.time.LocalDate;
import java.util.List;

import com.IMTFoods.UserManagement.utils.Gender;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInformationRequestDto {
	
	private String userFirstNameRequestDto;
	
	private String userLastNameRequestDto;
	
	private LocalDate userDateOfBirthRequestDto;
	
	private Gender genderRequestDto;
	
	private String userEmailRequestDto;
	
	private String userPasswordRequestDto;
	
	private String userPhoneNumberRequestDto;
	
	private List<UserAddressInformationRequestDto> userAddressInformationRequestDto;
	
	private List<AuthoritiesRequestDto> rolesRequestDto;
	
	
}
