package com.IMTFoods.FoodOrderManagement.builder;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.IMTFoods.FoodOrderManagement.dto.PaymentDetailsResponseDto;
import com.IMTFoods.FoodOrderManagement.dto.UpdateFoodOrderRequestDto;
import com.IMTFoods.FoodOrderManagement.dto.UpdateOrderItemsRequestDto;
import com.IMTFoods.FoodOrderManagement.dto.UpdatePaymentDetailsRequestDto;
import com.IMTFoods.FoodOrderManagement.model.FoodOrder;
import com.IMTFoods.FoodOrderManagement.model.OrderItems;
import com.IMTFoods.FoodOrderManagement.model.PaymentDetails;
import com.IMTFoods.FoodOrderManagement.service.PaymentDetailsService;

@Component
public class UpdateFoodOrderBuilder {
	
	private final PaymentDetailsService paymentDetailsService;
	private final FoodOrderBuilder foodOrderBuilder;
	
	public UpdateFoodOrderBuilder(PaymentDetailsService paymentDetailsSerivce, FoodOrderBuilder foodOrderBuilder) {
		this.paymentDetailsService = paymentDetailsSerivce;
		this.foodOrderBuilder = foodOrderBuilder;
	}

	public FoodOrder buildFoodOrderFromUpdateFoodOrderRequestDto(
			UpdateFoodOrderRequestDto updateFoodOrderRequestDto, long userId) {

		FoodOrder foodOrder = FoodOrder.builder()
					.orderId(updateFoodOrderRequestDto.getOrderId())
					.userId(userId)
					.orderItems(buildListOfOrderItemsFromUpdateOrderItemsRequestDto(updateFoodOrderRequestDto.getUpdatedOrderItemsRequestDto()))
					.paymentDetails(buildPaymentDetailsFromUpdatePaymentDetailsRequestDto(updateFoodOrderRequestDto.getUpdatePaymentDetailsRequestDto()))
					.orderTotalPrice(updateFoodOrderRequestDto.getUpdatedOrderTotalPrice())
					.build();
		
		return foodOrder;
	}
	
	private List<OrderItems> buildListOfOrderItemsFromUpdateOrderItemsRequestDto(List<UpdateOrderItemsRequestDto> updateOrderItemsRequestDtoList){
		
		List<OrderItems> orderItemsList = new ArrayList<>();
		for(UpdateOrderItemsRequestDto updateOrderItemsRequestDto : updateOrderItemsRequestDtoList) {
			OrderItems orderItems = buildOrderItemsFromUpdateOrderItemsRequestDto(updateOrderItemsRequestDto);
			orderItemsList.add(orderItems);
		}
		return orderItemsList;
	}
	
	public OrderItems buildOrderItemsFromUpdateOrderItemsRequestDto(UpdateOrderItemsRequestDto updateOrderItemsRequestDto) {
		
		OrderItems orderItems = OrderItems.builder()
					.restaurantFoodItemId(updateOrderItemsRequestDto.getUpdateOrderItemRequestDtoRestaurantFoodItemsId())
//					.foodItemName()
					.foodItemPrice(updateOrderItemsRequestDto.getUpdateOrderItemsRequestDtoFoodItemPrice())
					.foodQuantity(updateOrderItemsRequestDto.getUpdateOrderItemsRequestDtoQuantity())
					.build();
		
		Long orderItemId = updateOrderItemsRequestDto.getOrderItemId();
		if(orderItemId != null) {
			
			orderItems.setOrderItemId(updateOrderItemsRequestDto.getOrderItemId());
		}
		return orderItems;
	}

	public PaymentDetails buildPaymentDetailsFromUpdatePaymentDetailsRequestDto(UpdatePaymentDetailsRequestDto updatePaymentDetailsRequestDto) {
		
		Long transactionId = foodOrderBuilder.generateTransactionId();
		
		PaymentDetails paymentDetails = PaymentDetails.builder()
						.paymentDetailsId(updatePaymentDetailsRequestDto.getPaymentDetailsId())
						.orderPaymentActualPrice(getCurrentActualPrice(updatePaymentDetailsRequestDto.getPaymentDetailsId(), updatePaymentDetailsRequestDto.getUpdatePaymentDetailsRequestDtoOrderPaymentActualPrice()))
						.paymentTransactionId(transactionId != null ? transactionId : foodOrderBuilder.generateTransactionId())
						.build();
		paymentDetails.setOrderPaymentFinalPrice(paymentDetails.getOrderPaymentActualPrice()-paymentDetails.getDiscountAmount());
		
		return paymentDetails;
	}
	
	public double getCurrentActualPrice(long paymentDetailsId, double currentActualPrice) {		
		PaymentDetailsResponseDto paymentDetailsById = paymentDetailsService.getPaymentDetailsById(paymentDetailsId);
		if(paymentDetailsById.getPaymentDetailsResponseDtoOrderPaymentActualPrice() >= currentActualPrice) {
			//Refund Logic
		}
		return currentActualPrice;
	}
	
}
