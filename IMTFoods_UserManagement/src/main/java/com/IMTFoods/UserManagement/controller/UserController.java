package com.IMTFoods.UserManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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
import com.IMTFoods.UserManagement.service.UserInformationService;

@RestController
@RequestMapping("/user")
public class UserController {

	private final UserInformationService userInformationService;
	
	@Autowired
	public Environment environment;
	
	public UserController(UserInformationService userInformationService) {
		this.userInformationService = userInformationService;
	}
	
	@PostMapping("/signin")
	public ResponseEntity<UserInformationResponseDto> signInUser(@RequestBody UserInformationRequestDto userInformationRequestDto) {
		ResponseEntity<UserInformationResponseDto> signInUser = userInformationService.signInUser(userInformationRequestDto);
		return signInUser;
	}
	
	@GetMapping("{id}")
	public ResponseEntity<UserInformationResponseDto> getUserById(@PathVariable(name = "id") Long userId){
		ResponseEntity<UserInformationResponseDto> userById = userInformationService.getUserById(userId);
		String port = environment.getProperty("local.server.port");
		userById.getBody().setUserLastNameResponseDto(userById.getBody().getUserLastNameResponseDto()+" "+port);
		return userById;
	}
	
	@GetMapping("/allUser")
	public ResponseEntity<List<UserInformationResponseDto>> getAllUsers(){
		ResponseEntity<List<UserInformationResponseDto>> allUsers = null;
		try {
			allUsers = userInformationService.getAllUsers();
			return allUsers;
		} catch (NoContentFoundException e) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<UserInformationResponseDto> updateUserDetails(@RequestBody UpdateUserInformationRequestDto updateUserInformationRequestDto, @PathVariable(name="id") long userId){
		ResponseEntity<UserInformationResponseDto> updateUserDetails = userInformationService.updateUserDetails(updateUserInformationRequestDto, userId);
		return updateUserDetails;
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteUserInformationById(@PathVariable(name="id")long userId){
		ResponseEntity<String> deletedUserInformation = userInformationService.deleteUserInformationById(userId);
		return deletedUserInformation;
	}
	
	
	
}
