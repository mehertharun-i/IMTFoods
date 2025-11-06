package com.IMTFoods.UserManagement.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.IMTFoods.UserManagement.dto.UpdateUserInformationRequestDto;
import com.IMTFoods.UserManagement.dto.UserInformationRequestDto;
import com.IMTFoods.UserManagement.dto.UserInformationResponseDto;
import com.IMTFoods.UserManagement.exception.NoContentFoundException;
import com.IMTFoods.UserManagement.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/signin")
	public ResponseEntity<UserInformationResponseDto> signInUser(@RequestBody UserInformationRequestDto userInformationRequestDto) {
		ResponseEntity<UserInformationResponseDto> signInUser = userService.signInUser(userInformationRequestDto);
		return signInUser;
	}
	
	@GetMapping("{id}")
	public ResponseEntity<UserInformationResponseDto> getUserById(@PathVariable(name = "id") Long userId){
		ResponseEntity<UserInformationResponseDto> userById = userService.getUserById(userId);
		return userById;
	}
	
	@GetMapping("/allUser")
	public ResponseEntity<List<UserInformationResponseDto>> getAllUsers(){
		ResponseEntity<List<UserInformationResponseDto>> allUsers = null;
		try {
			allUsers = userService.getAllUsers();
			return allUsers;
		} catch (NoContentFoundException e) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			
		}
		
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<UserInformationResponseDto> updateUserDetails(@RequestBody UpdateUserInformationRequestDto updateUserInformationRequestDto, @PathVariable(name="id") long userId){
		ResponseEntity<UserInformationResponseDto> updateUserDetails = userService.updateUserDetails(updateUserInformationRequestDto, userId);
		return updateUserDetails;
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteUserInformationById(@PathVariable(name="id")long userId){
		ResponseEntity<String> deletedUserInformation = userService.deleteUserInformationById(userId);
		return deletedUserInformation;
	}
	
	
	
}
