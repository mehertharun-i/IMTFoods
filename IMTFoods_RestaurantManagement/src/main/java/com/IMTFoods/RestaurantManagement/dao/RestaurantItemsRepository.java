package com.IMTFoods.RestaurantManagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.IMTFoods.RestaurantManagement.model.RestaurantItems;

@Repository
public interface RestaurantItemsRepository extends JpaRepository<RestaurantItems, Long>{

}
