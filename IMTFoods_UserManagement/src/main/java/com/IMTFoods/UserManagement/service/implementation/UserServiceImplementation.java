package com.IMTFoods.UserManagement.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.IMTFoods.UserManagement.builder.UserInformationBuilder;
import com.IMTFoods.UserManagement.builder.UserInformationResponseDtoBuilder;
import com.IMTFoods.UserManagement.builder.UserInformationResponseDtoListBuilder;
import com.IMTFoods.UserManagement.dao.UserInformationRepository;
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
import com.IMTFoods.UserManagement.service.UserInformationService;

@Service
public class UserServiceImplementation implements UserInformationService{
	
	private final UserInformationRepository userInformationRepository;
	
	
	public UserServiceImplementation(UserInformationRepository userInformationRepository) {
		this.userInformationRepository = userInformationRepository;
	}


	@Override
	public ResponseEntity<UserInformationResponseDto> signInUser(UserInformationRequestDto userInformationRequestDto) {
		UserInformation userInformation = UserInformationBuilder.buildUserInformationFromUserInformationRequestDto(userInformationRequestDto);
		UserInformation savedUserInformation = userInformationRepository.save(userInformation);
		UserInformationResponseDto userInformationResponseDto = UserInformationResponseDtoBuilder.buildUserInformationResponseDtoFromUserInformation(savedUserInformation);
		return ResponseEntity.status(HttpStatus.OK).body(userInformationResponseDto);
		
	}

	@Override
	public ResponseEntity<UserInformationResponseDto> getUserById(Long userId) {
		UserInformation userInformation = userInformationRepository.findById(userId).orElseThrow( () -> new UserIdNotFoundException("Invalid User Id"));
		UserInformationResponseDto userInformationResponseDto = UserInformationResponseDtoBuilder.buildUserInformationResponseDtoFromUserInformation(userInformation);
		return ResponseEntity.status(HttpStatus.OK).body(userInformationResponseDto);
		
		
	}

	@Override
	public ResponseEntity<List<UserInformationResponseDto>> getAllUsers() throws NoContentFoundException {
		List<UserInformation> allUserList = userInformationRepository.findAll();
		if(allUserList.isEmpty()) {
			 throw new NoContentFoundException("Not Data Available");
		}else {
			List<UserInformationResponseDto> userInformationResponseDtoList = UserInformationResponseDtoListBuilder.buildListOfUserInformationResponseDtoFromUserInformation(allUserList);
			return ResponseEntity.status(HttpStatus.OK).body(userInformationResponseDtoList);
		}
	}

	@Override
	public ResponseEntity<UserInformationResponseDto> updateUserDetails(UpdateUserInformationRequestDto updateUserInformationRequestDto, long userId){
		UserInformation userInformation = userInformationRepository.findById(userId).orElseThrow( () -> new UserIdNotFoundException("Invalid UserId for update Operation"));
		
		updateUserInformationFromUpdateUserInforamtionRequestDto(updateUserInformationRequestDto, userInformation);
		
		UserInformation updatedUserInformation = userInformationRepository.save(userInformation);
		
		UserInformationResponseDto userInformationResponseDto = UserInformationResponseDtoBuilder.buildUserInformationResponseDtoFromUserInformation(updatedUserInformation);
				
		return ResponseEntity.status(HttpStatus.OK).body(userInformationResponseDto);
	}

	

	@Override
	public ResponseEntity<String> deleteUserInformationById(long userId) {
		UserInformation userInformation = userInformationRepository.findById(userId).orElseThrow( () -> new UserIdNotFoundException("Invalid User Id for Delete Operation"));
		userInformationRepository.deleteById(userInformation.getUserId());
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
		if(updateUserInformationRequestDto.getUserPhoneNumberReqeustDto() != null && updateUserInformationRequestDto.getUserPhoneNumberReqeustDto() != userInformation.getUserPhoneNumber()) {
			userInformation.setUserPhoneNumber(updateUserInformationRequestDto.getUserPhoneNumberReqeustDto());
		}
			userInformation.setUserDateOfBirth(updateUserInformationRequestDto.getUserDateOfBirthReqeustDto());
			userInformation.setGender(updateUserInformationRequestDto.getGenderReqeustDto());
		
		int count = 0;
		
		List<UserAddressInformation> updateUserAddressInformationList = new ArrayList<>(); 
		for(UserAddressInformationRequestDto updateSingleUserInformationRequestDto : updateUserInformationRequestDto.getUserAddressInformationReqeustDto()) {
			UserAddressInformation userAddressInformation = userInformation.getUserAddressInformation().get(count++);
			userAddressInformation.setUserHouseNumber(updateSingleUserInformationRequestDto.getUserHouseNumberRequestDto());
			userAddressInformation.setUserLandMark(updateSingleUserInformationRequestDto.getUserLandMarkRequestDto());
			userAddressInformation.setUserStreet(updateSingleUserInformationRequestDto.getUserStreetRequestDto());
			userAddressInformation.setUserDistrict(updateSingleUserInformationRequestDto.getUserDistrictRequestDto());
			userAddressInformation.setUserState(updateSingleUserInformationRequestDto.getUserStateRequestDto());
			userAddressInformation.setUserCountry(updateSingleUserInformationRequestDto.getUserCountryRequestDto());
			userAddressInformation.setUserPincode(updateSingleUserInformationRequestDto.getUserPincodeRequestDto());
			updateUserAddressInformationList.add(userAddressInformation);
		}
		
		userInformation.getUserAddressInformation().clear();
		userInformation.getUserAddressInformation().addAll(updateUserAddressInformationList);
//		userInformation.setUserAddressInformation(updateUserAddressInformationList);
		
		count = 0;
		
		List<Authorities> authoritiesList = new ArrayList<>();
		for(AuthoritiesRequestDto authorities : updateUserInformationRequestDto.getRolesRequestDto()) {
			Authorities authority = userInformation.getRoles().get(count++);
			authority.setRoles(authorities.getRoleRequestDto());
			authoritiesList.add(authority);
		}
		
		userInformation.getRoles().clear();
		userInformation.getRoles().addAll(authoritiesList);
//		userInformation.setRoles(authoritiesList);
		
		return userInformation;
	}
	
	

}
