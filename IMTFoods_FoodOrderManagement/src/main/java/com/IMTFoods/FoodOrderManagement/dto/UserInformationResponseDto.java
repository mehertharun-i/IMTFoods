package com.IMTFoods.FoodOrderManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInformationResponseDto {
	
	private String userFirstNameResponseDto;
	
	private String userLastNameResponseDto;
	
	private LocalDate userDateOfBirthResponseDto;
	
	private String userPhoneNumberResponseDto;
	
	private List<UserAddressInformationResponseDto> userAddressResponseDto;
	
	private List<AuthoritiesResponseDto> rolesResponseDto;

}
