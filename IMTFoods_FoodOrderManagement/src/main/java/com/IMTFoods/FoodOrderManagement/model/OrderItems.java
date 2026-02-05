package com.IMTFoods.FoodOrderManagement.model;

import jakarta.persistence.*;
import lombok.*;

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
