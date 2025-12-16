package com.IMTFoods.FoodOrderManagement.builder;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.IMTFoods.FoodOrderManagement.dao.FoodOrderRepository;
import com.IMTFoods.FoodOrderManagement.dao.PaymentStatusRepository;
import com.IMTFoods.FoodOrderManagement.dto.FoodOrderRequestDto;
import com.IMTFoods.FoodOrderManagement.dto.OrderItemsRequestDto;
import com.IMTFoods.FoodOrderManagement.dto.PaymentDetailsRequestDto;
import com.IMTFoods.FoodOrderManagement.model.FoodOrder;
import com.IMTFoods.FoodOrderManagement.model.OrderItems;
import com.IMTFoods.FoodOrderManagement.model.PaymentDetails;

@Component
public class FoodOrderRequestDtoBuilder {
	
	private final FoodOrderRepository foodOrderRepository;
	private final PaymentStatusRepository paymentStatusRepository;
	
	public FoodOrderRequestDtoBuilder(FoodOrderRepository foodOrderRepository, PaymentStatusRepository paymentStatusRepository) {
		this.paymentStatusRepository = paymentStatusRepository;
		this.foodOrderRepository = foodOrderRepository;
	}

	
	public FoodOrderRequestDto buildFoodOrderRequestDtoFromFoodOrder(FoodOrder foodOrder) {
		
		FoodOrderRequestDto.builder()
					.foodOrderRequestDtoUserId(foodOrder.getUserId())
					.foodOrderRequestDtoUserAddressId(foodOrder.getUserAddressId())
					.foodOrderRequestDtoRestaurantId(foodOrder.getRestaurantId())
					.foodOrderRequestDtoRestaurantAddressId(foodOrder.getRestaurantAddressId())
					.foodOrderRequestDtoOrderTotalPrice(foodOrder.getOrderTotalPrice())
					.foodOrderRequestDtoPaymentDetails(buildPaymentDetailsRequestDtoFromPaymentDetails(foodOrder.getPaymentDetails()))
					.foodOrderRequestDtoOrderItems(buildOrderItemsRequestDtoListFromOrderItemsList(foodOrder.getOrderItems()))
					.build();
					
		
		return null;
	}


	private List<OrderItemsRequestDto> buildOrderItemsRequestDtoListFromOrderItemsList(List<OrderItems> orderItemsList) {
		List<OrderItemsRequestDto> orderItemsRequestDtoList = new ArrayList<>();
		
		for(OrderItems orderItems : orderItemsList) {
			OrderItemsRequestDto orderItemsRequestDto = buildOrderItemsRequestDtoFromOrderItems(orderItems);
			orderItemsRequestDtoList.add(orderItemsRequestDto);
		}
		return orderItemsRequestDtoList;
	}


	public OrderItemsRequestDto buildOrderItemsRequestDtoFromOrderItems(OrderItems orderItems) {
		
		OrderItemsRequestDto orderItemsRequestDto = OrderItemsRequestDto.builder()
								.orderItemsRequestDtoRestaurantFoodItemsId(0)  //????
								.orderItemsRequestDtoFoodItemPrice(orderItems.getFoodItemPrice())
								.orderItemsRequestDtoQuantity(orderItems.getFoodQuantity())
								.build();
		return orderItemsRequestDto;
	}


	public PaymentDetailsRequestDto buildPaymentDetailsRequestDtoFromPaymentDetails(PaymentDetails paymentDetails) {

		PaymentDetailsRequestDto.builder()
									.
		
		return null;
	}
}
