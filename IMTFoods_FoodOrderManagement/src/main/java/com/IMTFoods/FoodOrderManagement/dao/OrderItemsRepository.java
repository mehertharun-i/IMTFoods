package com.IMTFoods.FoodOrderManagement.dao;

import com.IMTFoods.FoodOrderManagement.model.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemsRepository extends JpaRepository<OrderItems, Long> {

}
