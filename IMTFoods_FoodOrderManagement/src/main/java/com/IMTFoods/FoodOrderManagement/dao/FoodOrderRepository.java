package com.IMTFoods.FoodOrderManagement.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.IMTFoods.FoodOrderManagement.model.FoodOrder;

@Repository
public interface FoodOrderRepository extends JpaRepository<FoodOrder, Long> {
	
	Optional<FoodOrder> findByTrackingId(long trackingId);
	
	Optional<List<FoodOrder>> findByUserId(long userId);

}
