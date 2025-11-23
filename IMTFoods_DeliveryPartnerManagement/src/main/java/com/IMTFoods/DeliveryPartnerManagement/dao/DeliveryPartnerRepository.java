package com.IMTFoods.DeliveryPartnerManagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.IMTFoods.DeliveryPartnerManagement.model.DeliveryPartnerDetails;

@Repository
public interface DeliveryPartnerRepository extends JpaRepository<DeliveryPartnerDetails, Long>{

	
}
