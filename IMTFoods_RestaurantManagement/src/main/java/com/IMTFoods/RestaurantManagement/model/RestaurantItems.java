package com.IMTFoods.RestaurantManagement.model;

import com.IMTFoods.RestaurantManagement.utils.ItemCategory;
import com.IMTFoods.RestaurantManagement.utils.ItemType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "restaurant_items")
@Builder
public class RestaurantItems {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long restaurantItemId;
	
	private String itemName;
	
	private int itemPrice;
	
	@Enumerated(EnumType.STRING)
	private ItemCategory itemCategory;
	
	private double itemRating;
	
	private String itemDescription;
	
	@Enumerated(EnumType.STRING)
	private ItemType itemType;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "restaurant_details_id", nullable = false)
	private RestaurantDetails restaurantDetailsId;


	public RestaurantItems(String itemName, int itemPrice, ItemCategory itemCategory, double itemRating,
			String itemDescription, ItemType itemType, RestaurantDetails restaurantDetailsId) {
		super();
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.itemCategory = itemCategory;
		this.itemRating = itemRating;
		this.itemDescription = itemDescription;
		this.itemType = itemType;
		this.restaurantDetailsId = restaurantDetailsId;
	}
	
}
