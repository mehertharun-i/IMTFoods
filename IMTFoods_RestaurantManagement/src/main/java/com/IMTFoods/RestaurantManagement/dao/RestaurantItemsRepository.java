package com.IMTFoods.RestaurantManagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.IMTFoods.RestaurantManagement.model.RestaurantItems;

public interface RestaurantItemsRepository extends JpaRepository<RestaurantItems, Long>{

}
