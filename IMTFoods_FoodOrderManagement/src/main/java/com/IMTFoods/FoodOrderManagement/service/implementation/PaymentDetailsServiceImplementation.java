package com.IMTFoods.FoodOrderManagement.service.implementation;

import com.IMTFoods.FoodOrderManagement.builder.FoodOrderResponseDtoBuilder;
import com.IMTFoods.FoodOrderManagement.dao.PaymentDetailsRepository;
import com.IMTFoods.FoodOrderManagement.dto.PaymentDetailsResponseDto;
import com.IMTFoods.FoodOrderManagement.exception.PaymentDetailsIdNotFoundException;
import com.IMTFoods.FoodOrderManagement.model.PaymentDetails;
import com.IMTFoods.FoodOrderManagement.service.PaymentDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentDetailsServiceImplementation implements PaymentDetailsService {
	
	private final PaymentDetailsRepository paymentDetailsRepository;
	
	public PaymentDetailsServiceImplementation(PaymentDetailsRepository paymentDetailsRepository) {
		this.paymentDetailsRepository = paymentDetailsRepository;
	}

	@Override
	public PaymentDetailsResponseDto getPaymentDetailsById(long paymentDetailsId) {
		
		PaymentDetails paymentDetails = paymentDetailsRepository.findById(paymentDetailsId).orElseThrow( () -> new PaymentDetailsIdNotFoundException("Invalid Payment Details Id"));
		PaymentDetailsResponseDto paymentDetailsResponseDto = FoodOrderResponseDtoBuilder.buildPaymentDetailsResponseDtoFromPaymentDetails(paymentDetails);
		return paymentDetailsResponseDto;
	}

	@Override
	public List<PaymentDetailsResponseDto> getAllPaymentDetails() {
		
		List<PaymentDetails> allPaymentDetailsList = paymentDetailsRepository.findAll();
		
		List<PaymentDetailsResponseDto> paymentDetailsResponseDtoList = new ArrayList<>();
		for(PaymentDetails paymentDetails : allPaymentDetailsList) {
			PaymentDetailsResponseDto paymentDetailsResponseDto = FoodOrderResponseDtoBuilder.buildPaymentDetailsResponseDtoFromPaymentDetails(paymentDetails);
			paymentDetailsResponseDtoList.add(paymentDetailsResponseDto);
		}
		return paymentDetailsResponseDtoList;
	}

	@Override
	public void deletePaymentDetailsById(long paymentDetailsId) {
		PaymentDetails paymentDetails = paymentDetailsRepository.findById(paymentDetailsId).orElseThrow( () -> new PaymentDetailsIdNotFoundException("Invalid Payment Detials Id"));
		paymentDetailsRepository.deleteById(paymentDetails.getPaymentDetailsId());
	}
	
	

}
