package com.IMTFoods.FoodOrderManagement.controller;

import java.util.List;

import org.springframework.data.domain.Page;
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
import com.IMTFoods.FoodOrderManagement.dto.FoodOrderRequestDto;
import com.IMTFoods.FoodOrderManagement.dto.FoodOrderResponseDto;
import com.IMTFoods.FoodOrderManagement.dto.UpdateFoodOrderRequestDto;
import com.IMTFoods.FoodOrderManagement.service.FoodOrderService;


@RestController
@RequestMapping("/order")
public class FoodOrderController {

	private final FoodOrderService foodOrderService;
	
	public FoodOrderController(FoodOrderService foodOrderService) {
		this.foodOrderService = foodOrderService;
	}
	
	@PostMapping("/foodorder")
	public ResponseEntity<FoodOrderResponseDto> orderFood(@RequestBody FoodOrderRequestDto foodOrderRequestDto) throws Exception{
		FoodOrderResponseDto orderFood = foodOrderService.orderFood(foodOrderRequestDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(orderFood);
	}
	
	@PostMapping("/foodorder/all")
	public ResponseEntity<List<FoodOrderResponseDto>> orderListOfFoods(@RequestBody List<FoodOrderRequestDto> foodOrderRequestDtoList) throws Exception{
		List<FoodOrderResponseDto> listOfFoods = foodOrderService.orderListOfFoods(foodOrderRequestDtoList);
		return ResponseEntity.status(HttpStatus.CREATED).body(listOfFoods);
	}
	
	@GetMapping("/find/{id}")
	public ResponseEntity<FoodOrderResponseDto> getOrderedFoodDetailsById(@PathVariable("id") long orderId){
		FoodOrderResponseDto foodOrderResponseDto = foodOrderService.getOrderedFoodDetailsById(orderId);
		return ResponseEntity.status(HttpStatus.FOUND).body(foodOrderResponseDto);
	}
	
	@GetMapping("/find/all")
	public ResponseEntity<List<FoodOrderResponseDto>> getAllOrderedFoodDetails(){
		List<FoodOrderResponseDto> foodOrderResponseDto = foodOrderService.getAllOrderedFoodDetails();
		return ResponseEntity.status(HttpStatus.FOUND).body(foodOrderResponseDto);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteOrderedFood(@PathVariable("id") long orderedFoodId){
		foodOrderService.deleteOrderedFood(orderedFoodId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@GetMapping("/orderhistory/{id}")
	public ResponseEntity<Page<FoodOrderResponseDto>> orderedHistory(@PathVariable("id") long userId, @RequestParam("pageNum") int pageNumber, @RequestParam("PageSize") int pageSize){
		Page<FoodOrderResponseDto> foodOrderResponseDto = foodOrderService.orderedHistory(userId, pageNumber, pageSize);
		return ResponseEntity.status(HttpStatus.OK).body(foodOrderResponseDto);
	}

	@PostMapping("/foodreorder/{id}")
	public ResponseEntity<FoodOrderResponseDto> reOrderFood(@PathVariable("id") long orderedFoodId) throws Exception {
		FoodOrderResponseDto foodOrderResponseDto = foodOrderService.reOrderFood(orderedFoodId);
		return ResponseEntity.status(HttpStatus.OK).body(foodOrderResponseDto);
	}
	
	@PutMapping("/updateorderedfood/{userid}")
	public ResponseEntity<FoodOrderResponseDto> updateOrderFood(@PathVariable("userid") long userId, @RequestBody UpdateFoodOrderRequestDto updateFoodOrderRequestDto){
		FoodOrderResponseDto updatedOrderFood = foodOrderService.updateOrderFood(updateFoodOrderRequestDto, userId);
		return ResponseEntity.status(HttpStatus.OK).body(updatedOrderFood);
	}
	
}
