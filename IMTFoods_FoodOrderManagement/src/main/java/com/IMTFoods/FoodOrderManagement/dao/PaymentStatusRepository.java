package com.IMTFoods.FoodOrderManagement.dao;

import com.IMTFoods.FoodOrderManagement.model.PaymentDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentStatusRepository extends JpaRepository<PaymentDetails, Long>{

	Optional<PaymentDetails> findByPaymentTransactionId(long paymentTransactionId);
	
}
