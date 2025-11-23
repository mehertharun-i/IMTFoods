package com.IMTFoods.FoodOrderManagement.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.IMTFoods.FoodOrderManagement.builder.DeliveryPartnerAssignmentBuilder;
import com.IMTFoods.FoodOrderManagement.builder.FoodOrderBuilder;
import com.IMTFoods.FoodOrderManagement.builder.FoodOrderResponseDtoBuilder;
import com.IMTFoods.FoodOrderManagement.dao.FoodOrderRepository;
import com.IMTFoods.FoodOrderManagement.dto.DeliveryPartnerAssignmentRequestDto;
import com.IMTFoods.FoodOrderManagement.dto.DeliveryPartnerAssignmentResponseDto;
import com.IMTFoods.FoodOrderManagement.dto.FoodOrderRequestDto;
import com.IMTFoods.FoodOrderManagement.dto.FoodOrderResponseDto;
import com.IMTFoods.FoodOrderManagement.dto.RestaurantAddressResponseDto;
import com.IMTFoods.FoodOrderManagement.dto.UserAddressInformationResponseDto;
import com.IMTFoods.FoodOrderManagement.exception.OrderedFoodIdNotFoundException;
import com.IMTFoods.FoodOrderManagement.exception.UserAddressAndRestaurantAddressAreNotCloserException;
import com.IMTFoods.FoodOrderManagement.model.FoodOrder;
import com.IMTFoods.FoodOrderManagement.service.FoodOrderService;

@Service
public class FoodOrderServiceImplementation implements FoodOrderService {

	@Autowired
	private FoodOrderBuilder foodOrderBuilder;
	
	private final FoodOrderRepository foodOrderRepository;
	private final RestTemplate restTemplate;
	
	public FoodOrderServiceImplementation(FoodOrderRepository foodOrderRepository, RestTemplate restTemplate) {
		this.foodOrderRepository = foodOrderRepository;
		this.restTemplate = restTemplate;
	}
	
	@Override
	public FoodOrderResponseDto orderFood(FoodOrderRequestDto foodOrderRequestDto) throws UserAddressAndRestaurantAddressAreNotCloserException{
		
		UserAddressInformationResponseDto userAddress = fetchUserAddressInformationResponseDtoFromUserManagement(foodOrderRequestDto.getFoodOrderRequestDtoUserAddressId());
		RestaurantAddressResponseDto restaurantAddress = fetchRestaurantAddressResponseDtoFromRestaurantManagement(foodOrderRequestDto.getFoodOrderRequestDtoRestaurantAddressId());
		if(userAddress.getUserDistrictResponseDto().equals(restaurantAddress.getRestaurantDistrictResponseDto())) {
			FoodOrder orderedFood = foodOrderBuilder.buildFoodOrderFromFoodOrderRequestDto(foodOrderRequestDto);
			FoodOrder savedOrderedFood = foodOrderRepository.save(orderedFood);
			
			DeliveryPartnerAssignmentRequestDto deliveryPartnerAssignmentRequestDto= DeliveryPartnerAssignmentBuilder.buildDeliveryPartnerAssignmentRequestDtoFromFoodOrder(savedOrderedFood);
			DeliveryPartnerAssignmentResponseDto deliveryAssignmentDetails = ExecuteDeliveryAssignmentFromOrderedFoodDetails(deliveryPartnerAssignmentRequestDto);
			
			System.out.println("1. savedOrderedFood After saving for the First time  : "+savedOrderedFood);
			
			savedOrderedFood.setDeliveryAssignmentId(deliveryAssignmentDetails.getDeliveryPartnerAssignmentResponseDtoAssignmentId());
			
			System.out.println("2. savedOrderedFood Before saving second time : "+savedOrderedFood);
			
			FoodOrder savedOrderedFood1 = foodOrderRepository.save(savedOrderedFood);
			
			System.out.println("3. savedOrderedFood1 After saving the second time  : "+savedOrderedFood1);
			
			FoodOrderResponseDto foodOrderResponseDto = FoodOrderResponseDtoBuilder.buildFoodOrderResponseDtoFromFoodOrder(savedOrderedFood1);
			return foodOrderResponseDto;
		}else {
			 throw new UserAddressAndRestaurantAddressAreNotCloserException("User Address Location and Restaurant Address Location is far aray");
		}
	}
	
	
	@Override
	public FoodOrderResponseDto getOrderedFoodDetailsById(long orderId) {
		FoodOrder foodOrder = foodOrderRepository.findById(orderId).orElseThrow( () ->  new OrderedFoodIdNotFoundException("Invalid Ordered Food Id"));
		FoodOrderResponseDto foodOrderResponseDto = FoodOrderResponseDtoBuilder.buildFoodOrderResponseDtoFromFoodOrder(foodOrder);
		
		return foodOrderResponseDto;
	}
	
	@Override
	public List<FoodOrderResponseDto> getAllOrderedFoodDetails() {
		List<FoodOrder> foodOrderList = foodOrderRepository.findAll();
		List<FoodOrderResponseDto> foodOrderResponseDtoList = new ArrayList<>();
		for(FoodOrder foodOrder : foodOrderList) {
			FoodOrderResponseDto foodOrderResponseDto = FoodOrderResponseDtoBuilder.buildFoodOrderResponseDtoFromFoodOrder(foodOrder);
			foodOrderResponseDtoList.add(foodOrderResponseDto);
		}
		return foodOrderResponseDtoList;
	}
	
	
	
	private DeliveryPartnerAssignmentResponseDto ExecuteDeliveryAssignmentFromOrderedFoodDetails(DeliveryPartnerAssignmentRequestDto deliveryPartnerAssignmentRequestDto) {
		DeliveryPartnerAssignmentResponseDto deliveryPartnerAssignmentId = restTemplate.postForObject("http://localhost:9003/deliveryassignment/assign", deliveryPartnerAssignmentRequestDto, DeliveryPartnerAssignmentResponseDto.class);
		return deliveryPartnerAssignmentId; 
	}

	//fetching Details of User Address from UserManagement Service related logic
		private UserAddressInformationResponseDto fetchUserAddressInformationResponseDtoFromUserManagement(long userAddressId) {
			UserAddressInformationResponseDto userAddressInformationResponseDto = restTemplate.getForObject("http://localhost:9001/user/address/find/"+userAddressId, UserAddressInformationResponseDto.class);
			return userAddressInformationResponseDto;
		}
		
	//fetching details of Restaurant Address from RestaurantManagement Service related logic
		private RestaurantAddressResponseDto fetchRestaurantAddressResponseDtoFromRestaurantManagement(long restaurantAddressId) {
			RestaurantAddressResponseDto restaurantAddressResponseDto = restTemplate.getForObject("http://localhost:9002/restaurant/address/find/"+restaurantAddressId, RestaurantAddressResponseDto.class);
			return restaurantAddressResponseDto;
		}

		

		

	
}
