package com.IMTFoods.UserManagement.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Table(name = "favourite_food_items")
@Builder
public class FavouriteRestaurant {
	
	private long favouriteFoodItemsId;
	
	@Column(nullable = false)
	private long restaurantId;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_information_id", nullable = false)
	private long userInformationId;
	
	@Column(nullable = false)
	@Builder.Default
	private boolean isFavourite = false;

	public FavouriteRestaurant(long restaurantId, long userInformationId) {
		super();
		this.restaurantId = restaurantId;
		this.userInformationId = userInformationId;
	}

}
