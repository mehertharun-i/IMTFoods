package com.IMTFoods.RestaurantManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ImtFoodsRestaurantManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImtFoodsRestaurantManagementApplication.class, args);
	}

}
