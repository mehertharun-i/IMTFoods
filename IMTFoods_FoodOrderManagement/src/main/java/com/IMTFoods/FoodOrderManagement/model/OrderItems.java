package com.IMTFoods.FoodOrderManagement.model;

import jakarta.persistence.Column;
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
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_items")
@Builder
public class OrderItems {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long orderItemId;
	
	@Column(nullable = false)
	private long restaurantFoodItemId;
	
	@Column(nullable = false)
	private String foodItemName;
	
	@Column(nullable = false)
	private double foodItemPrice;
	
	@Column(nullable = false)
	private int foodQuantity;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "food_order_id", nullable = false)
	@ToString.Exclude
	private FoodOrder foodOrderId;

	public OrderItems(long restaurantFoodItemId, String foodItemName, double foodItemPrice, int foodQuantity,
			FoodOrder foodOrderId) {
		super();
		this.restaurantFoodItemId = restaurantFoodItemId;
		this.foodItemName = foodItemName;
		this.foodItemPrice = foodItemPrice;
		this.foodQuantity = foodQuantity;
		this.foodOrderId = foodOrderId;
	}
	
	
	
}
