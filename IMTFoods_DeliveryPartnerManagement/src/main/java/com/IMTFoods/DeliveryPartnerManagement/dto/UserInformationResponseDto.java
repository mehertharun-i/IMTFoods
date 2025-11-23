package com.IMTFoods.DeliveryPartnerManagement.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
