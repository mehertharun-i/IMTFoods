package com.IMTFoods.RestaurantManagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "restaurant_address")
@Builder
public class RestaurantAddress {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long restaurantAddressId;
	
	private String restaurantHouseNumber;
	
	private String restaurantStreet;
	
	private String restaurantLandMark;
	
	private String restaurantDistrict;
	
	private String restaurantState;
	
	private String restaurantCountry;
	
	private int restaurantPincode;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "restaurant_details_id", nullable = false)
	private RestaurantDetails restaurantDetailsId;

	public RestaurantAddress(String restaurantHouseNumber, String restaurantStreet, String restaurantLandMark,
			String restaurantDistrict, String restaurantState, String restaurantCountry, int restaurantPincode,
			RestaurantDetails restaurantDetailsId) {
		super();
		this.restaurantHouseNumber = restaurantHouseNumber;
		this.restaurantStreet = restaurantStreet;
		this.restaurantLandMark = restaurantLandMark;
		this.restaurantDistrict = restaurantDistrict;
		this.restaurantState = restaurantState;
		this.restaurantCountry = restaurantCountry;
		this.restaurantPincode = restaurantPincode;
		this.restaurantDetailsId = restaurantDetailsId;
	}

	
	
}
