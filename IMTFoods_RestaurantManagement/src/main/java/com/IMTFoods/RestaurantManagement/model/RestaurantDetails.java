package com.IMTFoods.RestaurantManagement.model;

import java.util.ArrayList;
import java.util.List;

import com.IMTFoods.RestaurantManagement.utils.RestaurantType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@Table(name="restaurant_details")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long restaurantId;
	
	@Column(nullable = false)
	private String restaurantName;
	
	@Column(nullable = false)
	private String restaurantOwnerName;
	
	@Enumerated(EnumType.STRING)
	private RestaurantType restaurantType;
	
	private double restaurantRating;
	
	@Column(nullable = false)
	private String restaurantPhoneNumber;
	
	@Column(nullable = false)
	private boolean isRestaurantOpened;
	
	@Builder.Default
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "restaurantDetailsId", orphanRemoval = true)
	@ToString.Exclude
	private List<RestaurantItems> restaurantItems = new ArrayList<>();

	@Builder.Default
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "restaurantDetailsId", orphanRemoval = true)
	@ToString.Exclude
	private List<RestaurantAddress> restaurantAddress = new ArrayList<>();

//	public void addRestaurantItem(RestaurantItems restaurantItems) {
//		this.restaurantItems.add(restaurantItems);
//		restaurantItems.setRestaurantDetailsId(this);
//	}
//	
//	public void removeRestaurantItem(RestaurantItems restaurantItems) {
//		this.restaurantItems.remove(restaurantItems);
//		restaurantItems.setRestaurantDetailsId(null);
//	}
//	
//	public void addRestaurantAddress(RestaurantAddress restaurantAddress) {
//		this.restaurantAddress.add(restaurantAddress);
//		restaurantAddress.setRestaurantDetailsId(this);
//	}
//	
//	public void removeRestaurantAddress(RestaurantAddress restaurantAddress) {
//		this.restaurantAddress.remove(restaurantAddress);
//		restaurantAddress.setRestaurantDetailsId(null);
//	}

	public RestaurantDetails(String restaurantName, String restaurantOwnerName, RestaurantType restaurantType,
			double restaurantRating, boolean isRestaurantOpened, List<RestaurantItems> restaurantItems,
			List<RestaurantAddress> restaurantAddress) {
		super();
		this.restaurantName = restaurantName;
		this.restaurantOwnerName = restaurantOwnerName;
		this.restaurantType = restaurantType;
		this.restaurantRating = restaurantRating;
		this.isRestaurantOpened = isRestaurantOpened;
		this.restaurantItems = restaurantItems;
		this.restaurantAddress = restaurantAddress;
	}
		
}
