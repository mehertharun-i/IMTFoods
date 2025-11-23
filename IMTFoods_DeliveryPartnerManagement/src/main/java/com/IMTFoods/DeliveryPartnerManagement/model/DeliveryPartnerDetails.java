package com.IMTFoods.DeliveryPartnerManagement.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.IMTFoods.DeliveryPartnerManagement.utils.CurrentStatus;

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

@Entity
@Data
@Table(name = "delivery_partner_details")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryPartnerDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long deliveryPartnerId;
	
	@Column(nullable = false, unique = true)
	private String deliveryPartnerName;
	
	@Column(nullable = false, unique = true)
	private String deliveryPartnerAadharNumber;
	
	@Column(nullable = false, unique = true)
	private String deliveryPartnerEmail;
	
	@Column(nullable = false)
	private String deliveryPartnerPassword;
	
	private LocalDate deliveryPartnerDateOfBirth;
	
	private String deliveryPartnerPhoneNumber;
	
	@Column(precision = 3, scale = 2)
	@Builder.Default
	private BigDecimal deliveryPartnerRating = new BigDecimal(5.0);
	
	@Column(updatable = false)
	@Builder.Default
	private Instant deliveryPartnerCreatedAt = Instant.now();
		
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private CurrentStatus deliveryPartnerCurrentStatus;
	
	@Column(nullable = false)
	private boolean isloggedIn;
	
	@Column(nullable = false)
	@Builder.Default
	private int totalAssignedDelivery = 0;
	
	@Builder.Default
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "deliveryPartnerDetailsId")
	@ToString.Exclude
	private List<DeliveryPartnerAddress> deliveryPartnerAddress = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "deliveryPartnerId")
	@ToString.Exclude
	@Builder.Default
	private List<DeliveryPartnerAssignments> deliveryPartnerAssignments = new ArrayList<>();

	
	public DeliveryPartnerDetails(String deliveryPartnerName, String deliveryPartnerAadharNumber,
			String deliveryPartnerEmail, String deliveryPartnerPassword, LocalDate deliveryPartnerDateOfBirth,
			String deliveryPartnerPhoneNumber, BigDecimal deliveryPartnerRating, Instant deliveryPartnerCreatedAt,
			Instant deliveryPartnerUpdatedAt, CurrentStatus deliveryPartnerCurrentStatus, boolean isloggedIn,
			int totalAssignedDelivery, List<DeliveryPartnerAddress> deliveryPartnerAddress,
			List<DeliveryPartnerAssignments> deliveryPartnerAssignments) {
		super();
		this.deliveryPartnerName = deliveryPartnerName;
		this.deliveryPartnerAadharNumber = deliveryPartnerAadharNumber;
		this.deliveryPartnerEmail = deliveryPartnerEmail;
		this.deliveryPartnerPassword = deliveryPartnerPassword;
		this.deliveryPartnerDateOfBirth = deliveryPartnerDateOfBirth;
		this.deliveryPartnerPhoneNumber = deliveryPartnerPhoneNumber;
		this.deliveryPartnerRating = deliveryPartnerRating;
		this.deliveryPartnerCreatedAt = deliveryPartnerCreatedAt;
		this.deliveryPartnerCurrentStatus = deliveryPartnerCurrentStatus;
		this.isloggedIn = isloggedIn;
		this.totalAssignedDelivery = totalAssignedDelivery;
		this.deliveryPartnerAddress = deliveryPartnerAddress;
		this.deliveryPartnerAssignments = deliveryPartnerAssignments;
	}
	
}
