package com.IMTFoods.UserManagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.IMTFoods.UserManagement.model.FavouriteRestaurant;

public interface FavouriteRestaurantRepository extends JpaRepository<FavouriteRestaurant, Long>{

}
