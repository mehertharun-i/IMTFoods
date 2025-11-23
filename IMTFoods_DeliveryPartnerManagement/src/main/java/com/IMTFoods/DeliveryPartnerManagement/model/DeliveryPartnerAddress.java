package com.IMTFoods.DeliveryPartnerManagement.model;

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

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "delivery_partner_address")
@Builder
public class DeliveryPartnerAddress {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long deliveryPartnerAddressId;
	
	private String deliveryPartnerAddressHouseNumber;
	
	private String deliveryPartnerAddressStreetName;
	
	private String deliveryPartnerAddressLandMarkName;
	
	private String deliveryPartnerAddressDistrictName;
	
	private String deliveryPartnerAddressStateName;
	
	private String deliveryPartnerAddressCountryName;
	
	private int deliveryPartnerAddressPinCode;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "delivery_partner_details_id", nullable = false)
	private DeliveryPartnerDetails deliveryPartnerDetailsId;


	public DeliveryPartnerAddress(String deliveryPartnerAddressHouseNumber, String deliveryPartnerAddressStreetName,
			String deliveryPartnerAddressLandMarkName, String deliveryPartnerAddressDistrictName,
			String deliveryPartnerAddressStateName, String deliveryPartnerAddressCountryName,
			int deliveryPartnerAddressPinCode, DeliveryPartnerDetails deliveryPartnerDetailsId) {
		super();
		this.deliveryPartnerAddressHouseNumber = deliveryPartnerAddressHouseNumber;
		this.deliveryPartnerAddressStreetName = deliveryPartnerAddressStreetName;
		this.deliveryPartnerAddressLandMarkName = deliveryPartnerAddressLandMarkName;
		this.deliveryPartnerAddressDistrictName = deliveryPartnerAddressDistrictName;
		this.deliveryPartnerAddressStateName = deliveryPartnerAddressStateName;
		this.deliveryPartnerAddressCountryName = deliveryPartnerAddressCountryName;
		this.deliveryPartnerAddressPinCode = deliveryPartnerAddressPinCode;
		this.deliveryPartnerDetailsId = deliveryPartnerDetailsId;
	}
	
	
	
}
