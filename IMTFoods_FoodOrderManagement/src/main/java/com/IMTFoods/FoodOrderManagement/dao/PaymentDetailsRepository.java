package com.IMTFoods.FoodOrderManagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.IMTFoods.FoodOrderManagement.model.PaymentDetails;

public interface PaymentDetailsRepository extends JpaRepository<PaymentDetails, Long>{

}
