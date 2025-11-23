package com.IMTFoods.FoodOrderManagement.builder;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.IMTFoods.FoodOrderManagement.dao.FoodOrderRepository;
import com.IMTFoods.FoodOrderManagement.dao.PaymentStatusRepository;
import com.IMTFoods.FoodOrderManagement.dto.FoodOrderRequestDto;
import com.IMTFoods.FoodOrderManagement.dto.OrderItemsRequestDto;
import com.IMTFoods.FoodOrderManagement.dto.PaymentDetailsRequestDto;
import com.IMTFoods.FoodOrderManagement.model.FoodOrder;
import com.IMTFoods.FoodOrderManagement.model.OrderItems;
import com.IMTFoods.FoodOrderManagement.model.PaymentDetails;
import com.IMTFoods.FoodOrderManagement.utils.OrderStatus;
import com.IMTFoods.FoodOrderManagement.utils.PaymentStatus;
import com.IMTFoods.FoodOrderManagement.utils.PaymentType;

@Component
public class FoodOrderBuilder {
	
	private final RestTemplate restTemplate;
	
	private final PaymentStatusRepository paymentStatusRepository;
	private final FoodOrderRepository foodOrderRepository;
	
//	@Autowired
	public FoodOrderBuilder(RestTemplate restTemplate, PaymentStatusRepository paymentStatusRepository, FoodOrderRepository foodOrderRepository) {
		this.restTemplate = restTemplate;
		this.paymentStatusRepository = paymentStatusRepository;
		this.foodOrderRepository = foodOrderRepository;
	}
	
	
//	private static double totalPrice = 0;
	
	private FoodOrder foodOrder;
	
	private double totalPrice;
	
	public FoodOrder buildFoodOrderFromFoodOrderRequestDto(FoodOrderRequestDto foodOrderRequestDto) {
		
		 foodOrder = FoodOrder.builder()
					.userId(foodOrderRequestDto.getFoodOrderRequestDtoUserId())
					.userAddressId(foodOrderRequestDto.getFoodOrderRequestDtoUserAddressId())
					.restaurantId(foodOrderRequestDto.getFoodOrderRequestDtoRestaurantId())
					.restaurantAddressId(foodOrderRequestDto.getFoodOrderRequestDtoRestaurantAddressId())
					.deliveryPartnerId(fetchTheDeliveryPartnerWhoIsAvailable()) 
					.orderItems(buildListOfOrderItemFromOrderItemRequestDto(foodOrderRequestDto.getFoodOrderRequestDtoorderItems()))
					.orderTotalPrice(totalPrice)
					.paymentDetails(buildPaymentDetailsFromPaymentDetailsRequestDto(foodOrderRequestDto.getFoodOrderRequestDtoPaymentDetails()))
					.build();
		 
		foodOrder.setOrderStatus(foodOrder.getPaymentDetails().getPaymentStatus().equals(PaymentStatus.SUCCESSFUL) ? OrderStatus.PREPARING_ORDER : foodOrder.getPaymentDetails().getPaymentStatus().equals(PaymentStatus.FAILED) ? OrderStatus.FAILED : OrderStatus.PENDING);
		foodOrder.setTrackingId(foodOrder.getOrderStatus().equals(OrderStatus.PREPARING_ORDER) ? generateTrackingId() : 0);
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
	


	//Delivery Partner Id related Logic
	private long fetchTheDeliveryPartnerWhoIsAvailable() {
		Long availableDeliveryPartner = restTemplate.getForObject("http://localhost:9003/deliveryPartner/all/available", Long.class);
		return availableDeliveryPartner;
	}
	
	
//Order Items List related Logic
	private List<OrderItems> buildListOfOrderItemFromOrderItemRequestDto(List<OrderItemsRequestDto> orderItemsRequestDtoList) {
		totalPrice = 0;
		List<OrderItems> orderItemsList = new ArrayList<>();
		for(OrderItemsRequestDto orderItemRequestDto : orderItemsRequestDtoList) {
			OrderItems orderItems = buildOrderItemFromOrderItemRequestDto(orderItemRequestDto);
			totalPrice += orderItems.getFoodItemPrice();
			orderItemsList.add(orderItems);
		}
		
		return orderItemsList;
	}

	public OrderItems buildOrderItemFromOrderItemRequestDto(OrderItemsRequestDto orderItemRequestDto) {
		
		
		OrderItems orderItems = OrderItems.builder()
					.restaurantFoodItemId(orderItemRequestDto.getOrderItemsRequestDtoRestaurantFoodItemsId())
					.foodItemName(fetchTheRestaurantFoodItemNameByFoodItemId(orderItemRequestDto.getOrderItemsRequestDtoRestaurantFoodItemsId()))
					.foodItemPrice(orderItemRequestDto.getOrderItemsRequestDtoFoodItemPrice())	//try to fit the TotalPrice logic in the parameter by eliminting at Line No.65
					.foodQuantity(orderItemRequestDto.getOrderItemsRequestDtoQuantity())
					.build();		
		return orderItems;
	}

	
	private String fetchTheRestaurantFoodItemNameByFoodItemId(long foodItemId) {
		String foodItemName = restTemplate.getForObject("http://localhost:9002/restaurantitems/fooditem/name/"+foodItemId, String.class);
		return foodItemName;
	}
	
//Paymenet Details List related logic

	//	private static List<PaymentDetails> buildListOfPaymentDetailsFromPaymentDetailsRequestDto(List<PaymentDetailsRequestDto> paymentDetailsRequestDtoList) {
	//		List<PaymentDetails> paymentDetailsList = new ArrayList<>();
	//		
	//		for(PaymentDetailsRequestDto paymentDetailsRequestDto : paymentDetailsRequestDtoList) {
	//			PaymentDetails paymentDetails = buildPaymentDetailsFromPaymentDetailsRequestDto(paymentDetailsRequestDto);
	//			paymentDetailsList.add(paymentDetails);
	//			
	//		}
	//		return paymentDetailsList;
	//	}

	public PaymentDetails buildPaymentDetailsFromPaymentDetailsRequestDto(
			PaymentDetailsRequestDto paymentDetailsRequestDto) {
		PaymentDetails paymentDetails = PaymentDetails.builder()
						.orderPaymentActualPrice(paymentDetailsRequestDto.getPaymentDetailsRequestDtoOrderPaymentActualPrice())
						.discountAmount(paymentDetailsRequestDto.getPaymentDetailsRequestDtoDiscountAmount())
						.orderPaymentFinalPrice(paymentDetailsRequestDto.getPaymentDetailsRequestDtoOrderPaymentFinalPrice())
						.paymentType(paymentDetailsRequestDto.getPaymentDetailsRequestDtoPaymentType())
						.paymentStatus(paymentDetailsRequestDto.getPaymentDetailsRequestDtoPaymentType().equals(PaymentType.CASH_ON_DELIVERY)? PaymentStatus.PENDING: PaymentStatus.SUCCESSFUL)
						.paymentTransactionId(generateTransactionId())
						.build();	
		return paymentDetails;
	}
	
	private long generateTransactionId() {
		SecureRandom secureRandom = new SecureRandom();
		
		Long randomId = secureRandom.nextLong();
		randomId = Math.abs(randomId)/1000000;
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
		randomId = Math.abs(randomId)/1000000;
		Optional<FoodOrder> foodOrder = foodOrderRepository.findByTrackingId(randomId); 
		if(foodOrder.isPresent()) {
			return generateTrackingId();
		}
		return randomId;
	}
}