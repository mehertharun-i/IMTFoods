package com.IMTFoods.RestaurantManagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.IMTFoods.RestaurantManagement.model.RestaurantAddress;

public interface RestaurantAddressRepository extends JpaRepository<RestaurantAddress, Long> {

}
