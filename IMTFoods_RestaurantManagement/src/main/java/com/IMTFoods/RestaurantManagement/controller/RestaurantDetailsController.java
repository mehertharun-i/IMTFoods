package com.IMTFoods.RestaurantManagement.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.IMTFoods.RestaurantManagement.dto.RestaurantDetailsRequestDto;
import com.IMTFoods.RestaurantManagement.dto.RestaurantDetailsResponseDto;
import com.IMTFoods.RestaurantManagement.service.RestaurantDetailsService;
import com.IMTFoods.RestaurantManagement.utils.RestaurantStatus;

@RestController
@RequestMapping("/restaurant")
public class RestaurantDetailsController {
	
	private final RestaurantDetailsService restaurantDetailsService;
		
	public RestaurantDetailsController(RestaurantDetailsService restaurantDetailsService) {
		this.restaurantDetailsService = restaurantDetailsService;
	}
	
	@PostMapping("/register")
	public ResponseEntity<RestaurantDetailsResponseDto> registerRestaurantDetails(@RequestBody RestaurantDetailsRequestDto restaurantDetailsRequestDto){
		RestaurantDetailsResponseDto registeredRestaurant = restaurantDetailsService.registerRestaurantDetails(restaurantDetailsRequestDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(registeredRestaurant);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<RestaurantDetailsResponseDto> getRestaurantDetailsById(@PathVariable("id") long restaurantId){
		RestaurantDetailsResponseDto restaurantDetailsById = restaurantDetailsService.getRestaurantDetailsById(restaurantId);
		return ResponseEntity.status(HttpStatus.FOUND).body(restaurantDetailsById);
	}
	
	@GetMapping
	public ResponseEntity<List<RestaurantDetailsResponseDto>> getAllRestaurantDetails(){
		List<RestaurantDetailsResponseDto> allRestaurantDetails = restaurantDetailsService.getAllRestaurantDetails();
		return ResponseEntity.status(HttpStatus.FOUND).body(allRestaurantDetails);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteRestaurantById(@PathVariable("id") long restaurantId){
		restaurantDetailsService.deleteRestaurantById(restaurantId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<RestaurantDetailsResponseDto> updateRestaurantDetailsById(@PathVariable("id") long restaurantId, @RequestBody RestaurantDetailsRequestDto restaurantDetailsRequestDto){
		RestaurantDetailsResponseDto updatedRestaurantDetails = restaurantDetailsService.updateRestaurantDetailsById(restaurantId, restaurantDetailsRequestDto);
		return ResponseEntity.status(HttpStatus.OK).body(updatedRestaurantDetails);
	}
	
	@PutMapping("/updatestatus/{id}")
	public ResponseEntity<String> updateRestaurantStatusById(@PathVariable(name = "id") long restaurantDetailsId, @RequestParam(name = "status") RestaurantStatus restaurantStatus){
		String updateRestaurantStatusById = restaurantDetailsService.updateRestaurantStatusById(restaurantDetailsId, restaurantStatus);
		return ResponseEntity.status(HttpStatus.OK).body(updateRestaurantStatusById);
	}

}



























