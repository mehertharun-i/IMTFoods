package com.IMTFoods.FoodOrderManagement.builder;

import com.IMTFoods.FoodOrderManagement.dto.FoodOrderRequestDto;
import com.IMTFoods.FoodOrderManagement.dto.OrderItemsRequestDto;
import com.IMTFoods.FoodOrderManagement.dto.PaymentDetailsRequestDto;
import com.IMTFoods.FoodOrderManagement.model.FoodOrder;
import com.IMTFoods.FoodOrderManagement.model.OrderItems;
import com.IMTFoods.FoodOrderManagement.model.PaymentDetails;
import com.IMTFoods.FoodOrderManagement.utils.PaymentType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FoodOrderRequestDtoBuilder {
	
	public FoodOrderRequestDto buildFoodOrderRequestDtoFromFoodOrder(FoodOrder foodOrder) {
		FoodOrderRequestDto foodOrderRequestDto = FoodOrderRequestDto.builder()
					.foodOrderRequestDtoUserId(foodOrder.getUserId())
					.foodOrderRequestDtoUserAddressId(foodOrder.getUserAddressId())
					.foodOrderRequestDtoRestaurantId(foodOrder.getRestaurantId())
					.foodOrderRequestDtoRestaurantAddressId(foodOrder.getRestaurantAddressId())
					.foodOrderRequestDtoOrderTotalPrice(foodOrder.getOrderTotalPrice())
					.foodOrderRequestDtoPaymentDetails(buildPaymentDetailsRequestDtoFromPaymentDetails(foodOrder.getPaymentDetails()))
					.foodOrderRequestDtoOrderItems(buildOrderItemsRequestDtoListFromOrderItemsList(foodOrder.getOrderItems()))
					.build();
		
		return foodOrderRequestDto;
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
								.orderItemsRequestDtoRestaurantFoodItemsId(orderItems.getRestaurantFoodItemId())  //????
								.orderItemsRequestDtoFoodItemPrice(orderItems.getFoodItemPrice())
								.orderItemsRequestDtoQuantity(orderItems.getFoodQuantity())
								.build();
		return orderItemsRequestDto;
	}

	public PaymentDetailsRequestDto buildPaymentDetailsRequestDtoFromPaymentDetails(PaymentDetails paymentDetails) {

		PaymentDetailsRequestDto paymentDetailsRequestDto = PaymentDetailsRequestDto.builder()
									.paymentDetailsRequestDtoOrderPaymentActualPrice(paymentDetails.getOrderPaymentActualPrice())
									.paymentDetailsRequestDtoDiscountAmount(0)
									.paymentDetailsRequestDtoOrderPaymentFinalPrice(paymentDetails.getOrderPaymentFinalPrice())
									.paymentDetailsRequestDtoPaymentType(PaymentType.UPI)
									.build();
		return paymentDetailsRequestDto;
	}
	
}
