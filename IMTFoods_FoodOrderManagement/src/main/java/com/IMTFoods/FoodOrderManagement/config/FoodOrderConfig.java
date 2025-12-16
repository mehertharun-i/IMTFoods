package com.IMTFoods.FoodOrderManagement.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableSpringDataWebSupport(
		  pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO
		)
public class FoodOrderConfig {

    @Bean
    @LoadBalanced
    public RestTemplate loadRestTemplate() {
		return new RestTemplate();
	}

    @Bean
    public RestTemplate restTemplate() {
    	return new RestTemplate();
    }
}
