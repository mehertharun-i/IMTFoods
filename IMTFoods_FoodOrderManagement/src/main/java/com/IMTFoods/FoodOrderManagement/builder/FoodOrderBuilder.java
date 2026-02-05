package com.IMTFoods.FoodOrderManagement.builder;

import com.IMTFoods.FoodOrderManagement.dao.FoodOrderRepository;
import com.IMTFoods.FoodOrderManagement.dao.PaymentStatusRepository;
import com.IMTFoods.FoodOrderManagement.dto.DeliveryPartnerDetailsResponseDto;
import com.IMTFoods.FoodOrderManagement.dto.FoodOrderRequestDto;
import com.IMTFoods.FoodOrderManagement.dto.OrderItemsRequestDto;
import com.IMTFoods.FoodOrderManagement.dto.PaymentDetailsRequestDto;
import com.IMTFoods.FoodOrderManagement.model.FoodOrder;
import com.IMTFoods.FoodOrderManagement.model.OrderItems;
import com.IMTFoods.FoodOrderManagement.model.PaymentDetails;
import com.IMTFoods.FoodOrderManagement.utils.OrderStatus;
import com.IMTFoods.FoodOrderManagement.utils.PaymentStatus;
import com.IMTFoods.FoodOrderManagement.utils.PaymentType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class FoodOrderBuilder {
	
	private final RestTemplate restTemplate;
	private final RestTemplate loadRestTemplate;
	private final PaymentStatusRepository paymentStatusRepository;
	private final FoodOrderRepository foodOrderRepository;
	
	public FoodOrderBuilder(@Qualifier("restTemplate") RestTemplate restTemplate, @Qualifier("loadRestTemplate") RestTemplate loadRestTemplate, PaymentStatusRepository paymentStatusRepository, FoodOrderRepository foodOrderRepository) {
		this.restTemplate = restTemplate;
		this.loadRestTemplate = loadRestTemplate;
		this.paymentStatusRepository = paymentStatusRepository;
		this.foodOrderRepository = foodOrderRepository;
	}
	
	private static final int RANDOM_ID_DIVIDED_BY = 1000000;
	private FoodOrder foodOrder;
	
	private double totalPrice;
	
	public FoodOrder buildFoodOrderFromFoodOrderRequestDto(FoodOrderRequestDto foodOrderRequestDto) {
		
		List<OrderItems> listOfOrderItems = buildListOfOrderItemFromOrderItemRequestDto(foodOrderRequestDto.getFoodOrderRequestDtoOrderItems());
		DeliveryPartnerDetailsResponseDto getDeliveryPartnerId = fetchTheDeliveryPartnerWhoIsAvailable();
		PaymentDetails paymentDetails = buildPaymentDetailsFromPaymentDetailsRequestDto(foodOrderRequestDto.getFoodOrderRequestDtoPaymentDetails());
		
		foodOrder = FoodOrder.builder()
					.userId(foodOrderRequestDto.getFoodOrderRequestDtoUserId())
					.userAddressId(foodOrderRequestDto.getFoodOrderRequestDtoUserAddressId())
					.restaurantId(foodOrderRequestDto.getFoodOrderRequestDtoRestaurantId())
					.restaurantAddressId(foodOrderRequestDto.getFoodOrderRequestDtoRestaurantAddressId())
					.deliveryPartnerId(getDeliveryPartnerId.getDeliveryPartnerId()) 
					.orderItems(listOfOrderItems)
					.orderTotalPrice(totalPrice)
					.paymentDetails(paymentDetails)
					.build();
		 
		linkOrderStatusAndTrackingId(foodOrder, paymentDetails);
		linkfoodOrderToOrderItem(foodOrder);
		foodOrder.getPaymentDetails().setFoodOrderId(foodOrder);
		return foodOrder;
	}

	
	public void linkfoodOrderToOrderItem(FoodOrder foodOrder) {
		if(foodOrder != null) {
			for(OrderItems orderItem : foodOrder.getOrderItems()) {
				orderItem.setFoodOrderId(foodOrder);
			}
		}
	}
	
	private void linkOrderStatusAndTrackingId(FoodOrder foodOrder, PaymentDetails paymentDetails) {
		if(paymentDetails.getPaymentStatus().equals(PaymentStatus.SUCCESSFUL)) {
			foodOrder.setOrderStatus(OrderStatus.PREPARING_ORDER);
			foodOrder.setTrackingId(generateTrackingId());
		}else if(paymentDetails.getPaymentStatus().equals(PaymentStatus.FAILED)) {
			foodOrder.setOrderStatus(OrderStatus.FAILED);
		}else {
			foodOrder.setOrderStatus(OrderStatus.PENDING);
		}
	}


	//Delivery Partner Id related Logic
	private DeliveryPartnerDetailsResponseDto fetchTheDeliveryPartnerWhoIsAvailable() {
		DeliveryPartnerDetailsResponseDto availableDeliveryPartner = restTemplate.getForObject("http://localhost:9003/deliveryPartner/all/available", DeliveryPartnerDetailsResponseDto.class);
		return availableDeliveryPartner;
	}
	
	
//Order Items List related Logic
	private List<OrderItems> buildListOfOrderItemFromOrderItemRequestDto(List<OrderItemsRequestDto> orderItemsRequestDtoList) {
		totalPrice = 0;
		List<OrderItems> orderItemsList = new ArrayList<>();
		for(OrderItemsRequestDto orderItemRequestDto : orderItemsRequestDtoList) {
			OrderItems orderItems = buildOrderItemFromOrderItemRequestDto(orderItemRequestDto);
			totalPrice += orderItems.getFoodItemPrice()*orderItems.getFoodQuantity();
			
			orderItemsList.add(orderItems);
		}
		
		
		return orderItemsList;
	}

	public OrderItems buildOrderItemFromOrderItemRequestDto(OrderItemsRequestDto orderItemRequestDto) {
		long foodItemId = orderItemRequestDto.getOrderItemsRequestDtoRestaurantFoodItemsId();
		String foodItemName = fetchTheRestaurantFoodItemNameByFoodItemId(foodItemId);
		
		OrderItems orderItems = OrderItems.builder()
					.restaurantFoodItemId(foodItemId)
					.foodItemName(foodItemName)
					.foodItemPrice(orderItemRequestDto.getOrderItemsRequestDtoFoodItemPrice())	//try to fit the TotalPrice logic in the parameter by eliminting at Line No.65
					.foodQuantity(orderItemRequestDto.getOrderItemsRequestDtoQuantity())
					.build();		
		return orderItems;
	}

	
	private String fetchTheRestaurantFoodItemNameByFoodItemId(long foodItemId) {
		String foodItemName = loadRestTemplate.getForObject("http://IMTFoods-RestaurantManagement/restaurantitems/fooditem/name/"+foodItemId, String.class);
		return foodItemName;
	}


	public PaymentDetails buildPaymentDetailsFromPaymentDetailsRequestDto(
			PaymentDetailsRequestDto paymentDetailsRequestDto) {
		
		Long transactionId = generateTransactionId();
				
		PaymentDetails paymentDetails = PaymentDetails.builder()
						.orderPaymentActualPrice(paymentDetailsRequestDto.getPaymentDetailsRequestDtoOrderPaymentActualPrice())
						.discountAmount(paymentDetailsRequestDto.getPaymentDetailsRequestDtoDiscountAmount())
						.orderPaymentFinalPrice(paymentDetailsRequestDto.getPaymentDetailsRequestDtoOrderPaymentFinalPrice())
						.paymentType(paymentDetailsRequestDto.getPaymentDetailsRequestDtoPaymentType())
						.paymentStatus(paymentDetailsRequestDto.getPaymentDetailsRequestDtoPaymentType().equals(PaymentType.CASH_ON_DELIVERY)? PaymentStatus.PENDING: PaymentStatus.SUCCESSFUL)
						.paymentTransactionId(transactionId != null ? transactionId : generateTransactionId())
						.build();	
		return paymentDetails;
	}
	
	 long generateTransactionId() {

		SecureRandom secureRandom = new SecureRandom();
		
		Long randomId = secureRandom.nextLong();
		randomId = Math.abs(randomId)/RANDOM_ID_DIVIDED_BY;
		Optional<PaymentDetails> paymentDetails = paymentStatusRepository.findByPaymentTransactionId(randomId);
		if(paymentDetails.isPresent()) {
			return generateTransactionId();
		}
		
		return randomId;
	}

//TrackingId Generation related logic
	private long generateTrackingId() {
		
		SecureRandom secureRandom = new SecureRandom();
		
		Long randomId = secureRandom.nextLong();
		randomId = Math.abs(randomId)/RANDOM_ID_DIVIDED_BY;
		Optional<FoodOrder> foodOrder = foodOrderRepository.findByTrackingId(randomId); 
		if(foodOrder.isPresent()) {
			return generateTrackingId();
		}
		return randomId;
	}
}