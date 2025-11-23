package com.IMTFoods.FoodOrderManagement.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.IMTFoods.FoodOrderManagement.model.PaymentDetails;

public interface PaymentStatusRepository extends JpaRepository<PaymentDetails, Long>{

	Optional<PaymentDetails> findByPaymentTransactionId(long paymentTransactionId);
	
}
