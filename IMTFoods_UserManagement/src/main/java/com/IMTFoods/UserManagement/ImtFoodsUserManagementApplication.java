package com.IMTFoods.UserManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ImtFoodsUserManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImtFoodsUserManagementApplication.class, args);
	}

}