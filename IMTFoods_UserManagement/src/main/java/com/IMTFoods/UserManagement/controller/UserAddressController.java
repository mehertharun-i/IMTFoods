package com.IMTFoods.UserManagement.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.IMTFoods.UserManagement.dto.UserAddressInformationResponseDto;
import com.IMTFoods.UserManagement.service.UserAddressService;

@RestController
@RequestMapping("/user/address")
public class UserAddressController {

	private final UserAddressService userAddressService;
	
	public UserAddressController(UserAddressService userAddressService) {
		this.userAddressService = userAddressService;
	}
	
	@GetMapping("/find/{id}")
	public ResponseEntity<UserAddressInformationResponseDto> getUserAddressInformationById(@PathVariable("id") long userAddressId){
		UserAddressInformationResponseDto userAddressInformation = userAddressService.getUserAddressInformationById(userAddressId);
		return ResponseEntity.status(HttpStatus.FOUND).body(userAddressInformation);
	}
	
}
