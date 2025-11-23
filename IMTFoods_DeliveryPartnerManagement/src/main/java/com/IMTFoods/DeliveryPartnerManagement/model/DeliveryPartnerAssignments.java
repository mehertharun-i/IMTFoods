package com.IMTFoods.DeliveryPartnerManagement.model;

import java.time.Instant;

import com.IMTFoods.DeliveryPartnerManagement.utils.AssignmentStatus;

import jakarta.persistence.Column;
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

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "delivery_partner_assigments")
@Builder
public class DeliveryPartnerAssignments {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long assignmentId;

	private double currentDistanceDeliveryInKms;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	@Builder.Default
	private AssignmentStatus assignmentStatus = AssignmentStatus.PENDING_ASSIGNMENT;
	
	@Column(unique = true)
	@Builder.Default
	private long orderId = 0;
	
	@Column(nullable = false)
	@Builder.Default
	private long currentRestaurantId = 0;
	
	@Column(nullable = false)
	@Builder.Default
	private long currentRestaurantAddressId = 0;
	
	@Column(nullable = false)
	@Builder.Default
	private long currentOrderedUserId = 0;
	
	@Column(nullable = false)
	@Builder.Default
	private long currentOrderedUserAddressId = 0;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "delivery_partner_id", nullable = false)
	private DeliveryPartnerDetails deliveryPartnerId;
	
	@Column(updatable = false)
	@Builder.Default
	private Instant deliveryAssignmentCreatedAt = Instant.now();
	
	private Instant estimatedDeliveryTime;

	public DeliveryPartnerAssignments(double currentDistanceDeliveryInKms, AssignmentStatus assignmentStatus,
			long orderId, long currentRestaurantId, long currentRestaurantAddressId, long currentOrderedUserId,
			long currentOrderedUserAddressId, DeliveryPartnerDetails deliveryPartnerId,
			Instant deliveryAssignmentCreatedAt, Instant estimatedDeliveryTime) {
		super();
		this.currentDistanceDeliveryInKms = currentDistanceDeliveryInKms;
		this.assignmentStatus = assignmentStatus;
		this.orderId = orderId;
		this.currentRestaurantId = currentRestaurantId;
		this.currentRestaurantAddressId = currentRestaurantAddressId;
		this.currentOrderedUserId = currentOrderedUserId;
		this.currentOrderedUserAddressId = currentOrderedUserAddressId;
		this.deliveryPartnerId = deliveryPartnerId;
		this.deliveryAssignmentCreatedAt = deliveryAssignmentCreatedAt;
		this.estimatedDeliveryTime = estimatedDeliveryTime;
	}

	
}
