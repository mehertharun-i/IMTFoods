package com.IMTFoods.UserManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAddressInformationResponseDto {

	private String userHouseNumberResponseDto;
	
	private String userLandMarkResponseDto;
	
	private String userStreetResponseDto;
	
	private String userDistrictResponseDto;
	
	private String userStateResponseDto;
	
	private String userCountryResponseDto;
	
	private int userPincodeResponseDto;
	
}
