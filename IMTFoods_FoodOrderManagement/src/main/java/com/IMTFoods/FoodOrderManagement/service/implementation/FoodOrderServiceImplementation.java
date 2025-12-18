package com.IMTFoods.FoodOrderManagement.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.IMTFoods.FoodOrderManagement.builder.DeliveryPartnerAssignmentBuilder;
import com.IMTFoods.FoodOrderManagement.builder.FoodOrderBuilder;
import com.IMTFoods.FoodOrderManagement.builder.FoodOrderRequestDtoBuilder;
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
import com.IMTFoods.FoodOrderManagement.utils.CurrentStatus;

@Service
public class FoodOrderServiceImplementation implements FoodOrderService {

	private final FoodOrderBuilder foodOrderBuilder;
	private final DeliveryPartnerAssignmentBuilder deliveryPartnerAssignmentBuilder;
	private final FoodOrderRepository foodOrderRepository;
	private final FoodOrderResponseDtoBuilder foodOrderResponseDtoBuilder;
	private final RestTemplate restTemplate;
	private final RestTemplate loadRestTemplate;
	private final FoodOrderRequestDtoBuilder foodOrderRequestDtoBuilder;
	
	public FoodOrderServiceImplementation(FoodOrderRepository foodOrderRepository, @Qualifier("restTemplate") RestTemplate restTemplate,
			DeliveryPartnerAssignmentBuilder deliveryPartnerAssignmentBuilder, FoodOrderBuilder foodOrderBuilder,
			FoodOrderResponseDtoBuilder foodOrderResponseDtoBuilder, @Qualifier("loadRestTemplate") RestTemplate loadRestTemplate, FoodOrderRequestDtoBuilder foodOrderRequestDtoBuilder ) {
		this.foodOrderRepository = foodOrderRepository;
		this.restTemplate = restTemplate;
		this.deliveryPartnerAssignmentBuilder = deliveryPartnerAssignmentBuilder;
		this.foodOrderBuilder = foodOrderBuilder;
		this.foodOrderResponseDtoBuilder = foodOrderResponseDtoBuilder;
		this.loadRestTemplate = loadRestTemplate;
		this.foodOrderRequestDtoBuilder = foodOrderRequestDtoBuilder;
	}
	
	@Override
	public FoodOrderResponseDto orderFood(FoodOrderRequestDto foodOrderRequestDto) throws Exception{
		
		UserAddressInformationResponseDto userAddress = fetchUserAddressInformationResponseDtoFromUserManagement(foodOrderRequestDto.getFoodOrderRequestDtoUserAddressId());
		RestaurantAddressResponseDto restaurantAddress = fetchRestaurantAddressResponseDtoFromRestaurantManagement(foodOrderRequestDto.getFoodOrderRequestDtoRestaurantAddressId());
		
		if(userAddress.getUserDistrictResponseDto().equals(restaurantAddress.getRestaurantDistrictResponseDto())) {
			FoodOrder orderedFood = foodOrderBuilder.buildFoodOrderFromFoodOrderRequestDto(foodOrderRequestDto);
			FoodOrder initialSavedOrderedFood = foodOrderRepository.save(orderedFood);
			
			orderedFood.setDeliveryAssignmentId(ExecuteDeliveryAssignmentFromOrderedFoodDetails(initialSavedOrderedFood).getDeliveryPartnerAssignmentResponseDtoAssignmentId());
						
			FoodOrder finalSavedOrderedFood = foodOrderRepository.save(orderedFood);
			
			updateTheDeliveryPartnerCurrentStatus(finalSavedOrderedFood);
			updateTheDeliveryPartnerTotalAssignDeliveryCount(finalSavedOrderedFood.getDeliveryPartnerId());
			
			FoodOrderResponseDto foodOrderResponseDto = foodOrderResponseDtoBuilder.buildFoodOrderResponseDtoFromFoodOrder(finalSavedOrderedFood);
			return foodOrderResponseDto;
		}else {
			 throw new UserAddressAndRestaurantAddressAreNotCloserException("User Address Location and Restaurant Address Location is far aray");
		}
	}
	
	
	@Override
	public FoodOrderResponseDto getOrderedFoodDetailsById(long orderId) {
		FoodOrder foodOrder = foodOrderRepository.findById(orderId).orElseThrow( () ->  new OrderedFoodIdNotFoundException("Invalid Ordered Food Id"));
		FoodOrderResponseDto foodOrderResponseDto = foodOrderResponseDtoBuilder.buildFoodOrderResponseDtoFromFoodOrder(foodOrder);
		
		return foodOrderResponseDto;
	}
	
	@Override
	public List<FoodOrderResponseDto> getAllOrderedFoodDetails() {
		List<FoodOrder> foodOrderList = foodOrderRepository.findAll();
		List<FoodOrderResponseDto> foodOrderResponseDtoList = new ArrayList<>();
		for(FoodOrder foodOrder : foodOrderList) {
			FoodOrderResponseDto foodOrderResponseDto = foodOrderResponseDtoBuilder.buildFoodOrderResponseDtoFromFoodOrder(foodOrder);
			foodOrderResponseDtoList.add(foodOrderResponseDto);
		}
		return foodOrderResponseDtoList;
	}
	
	
	
	private DeliveryPartnerAssignmentResponseDto ExecuteDeliveryAssignmentFromOrderedFoodDetails(FoodOrder orderedFood) throws NullPointerException {
		DeliveryPartnerAssignmentRequestDto deliveryPartnerAssignmentRequestDto= deliveryPartnerAssignmentBuilder.buildDeliveryPartnerAssignmentRequestDtoFromFoodOrder(orderedFood);
		DeliveryPartnerAssignmentResponseDto deliveryPartnerAssignment = restTemplate.postForObject("http://localhost:9003/deliveryassignment/assign", deliveryPartnerAssignmentRequestDto, DeliveryPartnerAssignmentResponseDto.class);
		if(deliveryPartnerAssignment == null) {
			throw new NullPointerException();
		}
		return deliveryPartnerAssignment; 
	}

	//fetching Details of User Address from UserManagement Service related logic
		private UserAddressInformationResponseDto fetchUserAddressInformationResponseDtoFromUserManagement(long userAddressId) {
			UserAddressInformationResponseDto userAddressInformationResponseDto = loadRestTemplate.getForObject("http://IMTFoods-UserManagement/address/find/"+userAddressId, UserAddressInformationResponseDto.class);
			return userAddressInformationResponseDto;
		}
		
	//fetching details of Restaurant Address from RestaurantManagement Service related logic
		private RestaurantAddressResponseDto fetchRestaurantAddressResponseDtoFromRestaurantManagement(long restaurantAddressId) {
			RestaurantAddressResponseDto restaurantAddressResponseDto = loadRestTemplate.getForObject("http://IMTFoods-RestaurantManagement/restaurant/address/find/"+restaurantAddressId, RestaurantAddressResponseDto.class);
			return restaurantAddressResponseDto;
		}

		private void updateTheDeliveryPartnerCurrentStatus(FoodOrder foodOrder) {
			long deliveryPartnerId = foodOrder.getDeliveryPartnerId();
			CurrentStatus deliveryPartnerCurrentStatus = CurrentStatus.ON_DELIVERY;
			restTemplate.exchange("http://localhost:9003/deliveryPartner/update/currentstatus/"+deliveryPartnerId+"?status="+deliveryPartnerCurrentStatus, HttpMethod.PUT, null, Void.class);
		}
		
		private void updateTheDeliveryPartnerTotalAssignDeliveryCount(long deliveryPartnerId) {
			restTemplate.exchange("http://localhost:9003/deliveryPartner/update/deliverycount/"+deliveryPartnerId, HttpMethod.PUT, null, Void.class);
		}

		@Override
		public void deleteOrderedFood(long orderedFoodId) {
			FoodOrder foodOrder = foodOrderRepository.findById(orderedFoodId).orElseThrow( () -> new OrderedFoodIdNotFoundException("Invalid Ordered Food ID"));
			foodOrderRepository.deleteById(foodOrder.getOrderId());
		}

		@Override
		public List<FoodOrderResponseDto> orderListOfFoods(List<FoodOrderRequestDto> foodOrderRequestDtoList) throws Exception {
			List<FoodOrderResponseDto> foodOrderResponseDtoList = new ArrayList<>();
			
			for(FoodOrderRequestDto foodOrderRequestDto : foodOrderRequestDtoList) {
				FoodOrderResponseDto orderFood = orderFood(foodOrderRequestDto);
				foodOrderResponseDtoList.add(orderFood);
			}
			return foodOrderResponseDtoList;
		}

		@Override
		public Page<FoodOrderResponseDto> orderedHistory(long userId, int pageNumber, int pageSize) {
			
			Sort sort = Sort.by(Direction.DESC, "orderId");
			PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);
			
			Page<FoodOrder> foodOrderListByUserId = foodOrderRepository.findByUserId(userId, pageRequest);
			List<FoodOrder> foodOrderList = foodOrderListByUserId.getContent();
			List<FoodOrderResponseDto> foodOrderResponseDtoList = new ArrayList<>();
			
			for(FoodOrder foodOrder : foodOrderList) {
				FoodOrderResponseDto foodOrderResponseDto = foodOrderResponseDtoBuilder.buildFoodOrderResponseDtoFromFoodOrder(foodOrder);
				foodOrderResponseDtoList.add(foodOrderResponseDto);
			}
			
			PageImpl<FoodOrderResponseDto> pageImpl = new PageImpl<FoodOrderResponseDto>(foodOrderResponseDtoList, pageRequest, foodOrderListByUserId.getTotalElements());
			return pageImpl;
		}

		@Override
		public FoodOrderResponseDto reOrderFood(long orderedFoodId) throws Exception {
			
			FoodOrder foodOrder = foodOrderRepository.findById(orderedFoodId).orElseThrow( () -> new OrderedFoodIdNotFoundException("Invalid Ordered Food Id"));
			FoodOrderRequestDto foodOrderRequestDto = foodOrderRequestDtoBuilder.buildFoodOrderRequestDtoFromFoodOrder(foodOrder);
			FoodOrderResponseDto reOrderFood = orderFood(foodOrderRequestDto);
			return reOrderFood;
		}

}
