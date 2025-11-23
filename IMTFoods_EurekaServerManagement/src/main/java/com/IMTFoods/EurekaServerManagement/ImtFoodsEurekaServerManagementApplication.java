package com.IMTFoods.EurekaServerManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ImtFoodsEurekaServerManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImtFoodsEurekaServerManagementApplication.class, args);
	}

}
