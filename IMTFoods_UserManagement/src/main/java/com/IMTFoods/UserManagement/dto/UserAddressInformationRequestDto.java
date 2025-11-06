package com.IMTFoods.UserManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAddressInformationRequestDto {

	private String userHouseNumberRequestDto;
	
	private String userLandMarkRequestDto;
	
	private String userStreetRequestDto;
	
	private String userDistrictRequestDto;
	
	private String userStateRequestDto;
	
	private String userCountryRequestDto;
	
	private int userPincodeRequestDto;
		
}
