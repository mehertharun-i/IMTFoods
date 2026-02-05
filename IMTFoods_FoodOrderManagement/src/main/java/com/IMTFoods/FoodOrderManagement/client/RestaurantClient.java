package com.IMTFoods.FoodOrderManagement.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "IMTFoods-RestaurantManagement")
public interface RestaurantClient {
	

}
