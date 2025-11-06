package com.IMTFoods.RestaurantManagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="resturant_details")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long restaurantId;
	
	private String restaurantName;
	
	private String restaurentOwnerName;
	

	
	
	
}
