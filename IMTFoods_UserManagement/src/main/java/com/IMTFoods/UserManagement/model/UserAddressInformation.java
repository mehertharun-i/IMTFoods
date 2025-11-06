package com.IMTFoods.UserManagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_address")
@Builder
public class UserAddressInformation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userAddressId;
	
	private String userHouseNumber;
	
	private String userLandMark;
	
	private String userStreet;
	
	private String userDistrict;
	
	private String userState;
	
	private String userCountry;
	
	private int userPincode;

	public UserAddressInformation(String userHouseNumber, String userLandMark, String userStreet, String userDistrict,
			String userState, String userCountry, int userPincode) {
		this.userHouseNumber = userHouseNumber;
		this.userLandMark = userLandMark;
		this.userStreet = userStreet;
		this.userDistrict = userDistrict;
		this.userState = userState;
		this.userCountry = userCountry;
		this.userPincode = userPincode;
	}
	
	

}
