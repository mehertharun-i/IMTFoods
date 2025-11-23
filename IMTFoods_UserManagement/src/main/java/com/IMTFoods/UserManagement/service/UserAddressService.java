package com.IMTFoods.UserManagement.service;

import com.IMTFoods.UserManagement.dto.UserAddressInformationResponseDto;

public interface UserAddressService {

	UserAddressInformationResponseDto getUserAddressInformationById(long userAddressId);

}
