package com.IMTFoods.FoodOrderManagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.IMTFoods.FoodOrderManagement.model.OrderItems;

public interface OrderItemsRepository extends JpaRepository<OrderItems, Long> {

}
