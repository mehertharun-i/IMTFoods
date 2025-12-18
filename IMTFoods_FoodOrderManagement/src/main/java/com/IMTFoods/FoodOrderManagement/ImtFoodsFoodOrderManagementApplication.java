package com.IMTFoods.FoodOrderManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ImtFoodsFoodOrderManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImtFoodsFoodOrderManagementApplication.class, args);
	}

}
