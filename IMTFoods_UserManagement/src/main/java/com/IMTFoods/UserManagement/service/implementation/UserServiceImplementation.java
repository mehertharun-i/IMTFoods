package com.IMTFoods.UserManagement.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.IMTFoods.UserManagement.builder.UserInformationBuilder;
import com.IMTFoods.UserManagement.builder.UserInformationResponseDtoBuilder;
import com.IMTFoods.UserManagement.builder.UserInformationResponseDtoListBuilder;
import com.IMTFoods.UserManagement.dao.UserRepository;
import com.IMTFoods.UserManagement.dto.AuthoritiesRequestDto;
import com.IMTFoods.UserManagement.dto.UpdateUserInformationRequestDto;
import com.IMTFoods.UserManagement.dto.UserAddressInformationRequestDto;
import com.IMTFoods.UserManagement.dto.UserInformationRequestDto;
import com.IMTFoods.UserManagement.dto.UserInformationResponseDto;
import com.IMTFoods.UserManagement.exception.NoContentFoundException;
import com.IMTFoods.UserManagement.exception.UserIdNotFoundException;
import com.IMTFoods.UserManagement.model.Authorities;
import com.IMTFoods.UserManagement.model.UserAddressInformation;
import com.IMTFoods.UserManagement.model.UserInformation;
import com.IMTFoods.UserManagement.service.UserService;

@Service
public class UserServiceImplementation implements UserService{
	
	private final UserRepository userRepository;
	
	public UserServiceImplementation(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public ResponseEntity<UserInformationResponseDto> signInUser(UserInformationRequestDto userInformationRequestDto) {
		UserInformation userInformation = UserInformationBuilder.buildUserInformationFromUserInformationRequestDto(userInformationRequestDto);
		UserInformation savedUserInformation = userRepository.save(userInformation);
		UserInformationResponseDto userInformationResponseDto = UserInformationResponseDtoBuilder.buildUserInformationResponseDtoFromUserInformation(savedUserInformation);
		return ResponseEntity.status(HttpStatus.OK).body(userInformationResponseDto);
		
	}

	@Override
	public ResponseEntity<UserInformationResponseDto> getUserById(Long userId) {
		UserInformation userInformation = userRepository.findById(userId).orElseThrow( () -> new UserIdNotFoundException("Invalid User Id"));
		UserInformationResponseDto userInformationResponseDto = UserInformationResponseDtoBuilder.buildUserInformationResponseDtoFromUserInformation(userInformation);
		return ResponseEntity.status(HttpStatus.OK).body(userInformationResponseDto);
		
		
	}

	@Override
	public ResponseEntity<List<UserInformationResponseDto>> getAllUsers() throws NoContentFoundException {
		List<UserInformation> allUserList = userRepository.findAll();
		if(allUserList.isEmpty()) {
			 throw new NoContentFoundException("Not Data Available");
		}else {
			List<UserInformationResponseDto> userInformationResponseDtoList = UserInformationResponseDtoListBuilder.buildListOfUserInformationResponseDtoFromUserInformation(allUserList);
			return ResponseEntity.status(HttpStatus.OK).body(userInformationResponseDtoList);
		}
	}

	@Override
	public ResponseEntity<UserInformationResponseDto> updateUserDetails(UpdateUserInformationRequestDto updateUserInformationRequestDto, long userId){
		UserInformation userInformation = userRepository.findById(userId).orElseThrow( () -> new UserIdNotFoundException("Invalid UserId for update Operation"));
		
		updateUserInformationFromUpdateUserInforamtionRequestDto(updateUserInformationRequestDto, userInformation);
		
		UserInformation updatedUserInformation = userRepository.save(userInformation);
		
		UserInformationResponseDto userInformationResponseDto = UserInformationResponseDtoBuilder.buildUserInformationResponseDtoFromUserInformation(updatedUserInformation);
		
		
		return ResponseEntity.status(HttpStatus.OK).body(userInformationResponseDto);
	}

	

	@Override
	public ResponseEntity<String> deleteUserInformationById(long userId) {
		UserInformation userInformation = userRepository.findById(userId).orElseThrow( () -> new UserIdNotFoundException("Invalid User Id for Delete Operation"));
		userRepository.deleteById(userInformation.getUserId());
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Successfully Deleted the User Deleted Based on UserId -> UserName : "+userInformation.getUserId());
	}
	
	
	
	private UserInformation updateUserInformationFromUpdateUserInforamtionRequestDto(UpdateUserInformationRequestDto updateUserInformationRequestDto,
			UserInformation userInformation) {
		if(updateUserInformationRequestDto.getUserFirstNameReqeustDto() != null && updateUserInformationRequestDto.getUserFirstNameReqeustDto() != userInformation.getUserFirstName()) {
			userInformation.setUserFirstName(updateUserInformationRequestDto.getUserFirstNameReqeustDto());
		}
		
		if(updateUserInformationRequestDto.getUserLastNameReqeustDto() != null && updateUserInformationRequestDto.getUserLastNameReqeustDto() != userInformation.getUserLastName()) {
			userInformation.setUserLastName(updateUserInformationRequestDto.getUserLastNameReqeustDto());
		}
		
		if(updateUserInformationRequestDto.getUserDateOfBirthReqeustDto() != null && updateUserInformationRequestDto.getUserDateOfBirthReqeustDto() != userInformation.getUserDateOfBirth()) {
			userInformation.setUserDateOfBirth(updateUserInformationRequestDto.getUserDateOfBirthReqeustDto());
		}
		
		if(updateUserInformationRequestDto.getGenderReqeustDto() != null && updateUserInformationRequestDto.getGenderReqeustDto() != userInformation.getGender()) {
			userInformation.setGender(updateUserInformationRequestDto.getGenderReqeustDto());
		}
		
		if(updateUserInformationRequestDto.getUserPhoneNumberReqeustDto() != null && updateUserInformationRequestDto.getUserPhoneNumberReqeustDto() != userInformation.getUserPhoneNumber()) {
			userInformation.setUserPhoneNumber(updateUserInformationRequestDto.getUserPhoneNumberReqeustDto());
		}
		
		List<UserAddressInformation> updateUserAddressInformationList = new ArrayList<>(); 
		List<UserAddressInformationRequestDto> updateSingleUserAddressInformationDetails = updateUserInformationRequestDto.getUserAddressInformationReqeustDto();
		List<UserAddressInformation> userAddressInformationDetails = userInformation.getUserAddressInformation();
		
		for(int index = 0; index < updateSingleUserAddressInformationDetails.size(); index++) {
			
			if(updateSingleUserAddressInformationDetails.get(index).getUserHouseNumberRequestDto() != null && updateSingleUserAddressInformationDetails.get(index).getUserHouseNumberRequestDto() != userAddressInformationDetails.get(index).getUserHouseNumber()) {
				userAddressInformationDetails.get(index).setUserHouseNumber(updateSingleUserAddressInformationDetails.get(index).getUserHouseNumberRequestDto());
			}
			
			if(updateSingleUserAddressInformationDetails.get(index).getUserLandMarkRequestDto() != null && updateSingleUserAddressInformationDetails.get(index).getUserLandMarkRequestDto() != userAddressInformationDetails.get(index).getUserLandMark()) {
				userAddressInformationDetails.get(index).setUserLandMark(updateSingleUserAddressInformationDetails.get(index).getUserLandMarkRequestDto());
			}
			
			if(updateSingleUserAddressInformationDetails.get(index).getUserStreetRequestDto() != null && updateSingleUserAddressInformationDetails.get(index).getUserStreetRequestDto() != userAddressInformationDetails.get(index).getUserStreet()) {
				userAddressInformationDetails.get(index).setUserStreet(updateSingleUserAddressInformationDetails.get(index).getUserStreetRequestDto());
			}
			
			if(updateSingleUserAddressInformationDetails.get(index).getUserDistrictRequestDto() != null && updateSingleUserAddressInformationDetails.get(index).getUserDistrictRequestDto() != userAddressInformationDetails.get(index).getUserDistrict()) {
				userAddressInformationDetails.get(index).setUserDistrict(updateSingleUserAddressInformationDetails.get(index).getUserDistrictRequestDto());
			}
			
			if(updateSingleUserAddressInformationDetails.get(index).getUserStateRequestDto() != null && updateSingleUserAddressInformationDetails.get(index).getUserStateRequestDto() != userAddressInformationDetails.get(index).getUserState()) {
				userAddressInformationDetails.get(index).setUserState(updateSingleUserAddressInformationDetails.get(index).getUserStateRequestDto());
			}
			
			if(updateSingleUserAddressInformationDetails.get(index).getUserCountryRequestDto() != null && updateSingleUserAddressInformationDetails.get(index).getUserCountryRequestDto() != userAddressInformationDetails.get(index).getUserCountry()) {
				userAddressInformationDetails.get(index).setUserCountry(updateSingleUserAddressInformationDetails.get(index).getUserCountryRequestDto());
			}
			
			if(updateSingleUserAddressInformationDetails.get(index).getUserPincodeRequestDto() != 0 && updateSingleUserAddressInformationDetails.get(index).getUserPincodeRequestDto() != userAddressInformationDetails.get(index).getUserPincode()) {
				userAddressInformationDetails.get(index).setUserPincode(updateSingleUserAddressInformationDetails.get(index).getUserPincodeRequestDto());
			}
			updateUserAddressInformationList.add(userAddressInformationDetails.get(index));
		}
		
		userInformation.setUserAddressInformation(userAddressInformationDetails);
		
		List<Authorities> authoritiesList = new ArrayList<>();
		List<AuthoritiesRequestDto> updateSingleUserRolesDetails = updateUserInformationRequestDto.getRolesRequestDto();
		List<Authorities> userRolesDetails = userInformation.getRoles();
		
		for(int index = 0; index < updateSingleUserRolesDetails.size(); index++) {
			
			if(updateSingleUserRolesDetails.get(index).getRoleRequestDto() != null && updateSingleUserRolesDetails.get(index).getRoleRequestDto() != userRolesDetails.get(index).getRoles()) {
				userRolesDetails.get(index).setRoles(updateSingleUserRolesDetails.get(index).getRoleRequestDto());
			}
			
			authoritiesList.add(userRolesDetails.get(index));
		}
		
		userInformation.setRoles(authoritiesList);
		
		return userInformation;
	}
	
	

}
