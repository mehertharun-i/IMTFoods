package com.IMTFoods.RestaurantManagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.IMTFoods.RestaurantManagement.model.RestaurantDetails;

@Repository
public interface RestaurantDetailsRepository extends JpaRepository<RestaurantDetails, Long> {

}
