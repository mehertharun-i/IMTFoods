package com.IMTFoods.FoodOrderManagement.exception;

public class OrderedFoodIdNotFoundException extends RuntimeException{

	public OrderedFoodIdNotFoundException(String exceptionMessage) {
		super(exceptionMessage);
	}
}
