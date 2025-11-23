package com.IMTFoods.FoodOrderManagement.model;

import com.IMTFoods.FoodOrderManagement.utils.PaymentStatus;
import com.IMTFoods.FoodOrderManagement.utils.PaymentType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "payment_details")
public class PaymentDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long paymentDetailsId;
	
	@Column(nullable = false)
	private double orderPaymentActualPrice;
	
	@Builder.Default
	private double discountAmount = 0.0;
	
	@Column(nullable = false)
	private double orderPaymentFinalPrice;
	
	@Enumerated(EnumType.STRING)
	private PaymentType paymentType;
	
	@Enumerated(EnumType.STRING)
	private PaymentStatus paymentStatus;
	
	@Column(unique = true)
	@Builder.Default
	private long paymentTransactionId = 0;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "food_order_id", nullable = false)
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private FoodOrder foodOrderId;

	public PaymentDetails(double orderPaymentActualPrice, double discountAmount, double orderPaymentFinalPrice,
			PaymentType paymentType, PaymentStatus paymentStatus, long paymentTransactionId, FoodOrder foodOrderId) {
		super();
		this.orderPaymentActualPrice = orderPaymentActualPrice;
		this.discountAmount = discountAmount;
		this.orderPaymentFinalPrice = orderPaymentFinalPrice;
		this.paymentType = paymentType;
		this.paymentStatus = paymentStatus;
		this.paymentTransactionId = paymentTransactionId;
		this.foodOrderId = foodOrderId;
	}

}
