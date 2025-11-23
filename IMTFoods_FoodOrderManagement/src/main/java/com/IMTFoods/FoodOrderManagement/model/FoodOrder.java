package com.IMTFoods.FoodOrderManagement.model;

import java.util.ArrayList;
import java.util.List;

import com.IMTFoods.FoodOrderManagement.utils.OrderStatus;

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
import jakarta.persistence.OneToOne;
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
@Table(name = "food_order")
@Builder
public class FoodOrder {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long orderId;
	
	@Column(nullable = false)
	private long userId;
	
	@Column(nullable = false)
	private long userAddressId;
	
	@Column(nullable = false)
	private long restaurantId;
	
	@Column(nullable = false)
	private long restaurantAddressId;
	
	@Column(nullable = false)
	private long deliveryPartnerId;
	
	@Column(nullable = false)
	@Builder.Default
	private long deliveryAssignmentId = 0;
	
	@ToString.Exclude
	@Builder.Default
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "foodOrderId")
	private List<OrderItems> orderItems = new ArrayList<>();
	
	@Column(nullable = false)
	private double orderTotalPrice;
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "foodOrderId")
//	@ToString.Exclude
	private PaymentDetails paymentDetails;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	@Builder.Default
	private OrderStatus orderStatus = OrderStatus.PROCESSING;
	
	@Column(unique = true)
	@Builder.Default
	private long trackingId = 0;

	public FoodOrder(long userId, long userAddressId, long restaurantId, long restaurantAddressId,
			long deliveryPartnerId, List<OrderItems> orderItems, double orderTotalPrice,
			PaymentDetails paymentDetails, OrderStatus orderStatus, long trackingId) {
		super();
		this.userId = userId;
		this.userAddressId = userAddressId;
		this.restaurantId = restaurantId;
		this.restaurantAddressId = restaurantAddressId;
		this.deliveryPartnerId = deliveryPartnerId;
		this.orderItems = orderItems;
		this.orderTotalPrice = orderTotalPrice;
		this.paymentDetails = paymentDetails;
		this.orderStatus = orderStatus;
		this.trackingId = trackingId;
	}

}
