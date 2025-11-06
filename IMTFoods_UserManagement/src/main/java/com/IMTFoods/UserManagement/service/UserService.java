package com.IMTFoods.UserManagement.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.IMTFoods.UserManagement.dto.UpdateUserInformationRequestDto;
import com.IMTFoods.UserManagement.dto.UserInformationRequestDto;
import com.IMTFoods.UserManagement.dto.UserInformationResponseDto;
import com.IMTFoods.UserManagement.exception.NoContentFoundException;

public interface UserService {

	ResponseEntity<UserInformationResponseDto> signInUser(UserInformationRequestDto userInformationRequestDto);

	ResponseEntity<UserInformationResponseDto> getUserById(Long userId);

	ResponseEntity<List<UserInformationResponseDto>> getAllUsers() throws NoContentFoundException;

	ResponseEntity<UserInformationResponseDto> updateUserDetails(UpdateUserInformationRequestDto updateUserInformationRequestDto, long userId);

	ResponseEntity<String> deleteUserInformationById(long userId);
	
	
	
	
}
