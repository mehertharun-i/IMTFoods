package com.IMTFoods.RestaurantManagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.IMTFoods.RestaurantManagement.model.RestaurantDetails;

public interface RestaurantDetailsRepository extends JpaRepository<RestaurantDetails, Long> {

}
