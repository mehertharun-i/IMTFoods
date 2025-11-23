package com.IMTFoods.DeliveryPartnerManagement.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.IMTFoods.DeliveryPartnerManagement.dto.DeliveryPartnerDetailsRequestDto;
import com.IMTFoods.DeliveryPartnerManagement.dto.DeliveryPartnerDetailsResponseDto;
import com.IMTFoods.DeliveryPartnerManagement.dto.DeliveryPartnerDetailsUpdateRequestDto;
import com.IMTFoods.DeliveryPartnerManagement.dto.DeliveryPartnerDetailsUpdateResponseDto;
import com.IMTFoods.DeliveryPartnerManagement.service.DeliveryPartnerDetailsService;

@RestController
@RequestMapping("/deliveryPartner")
public class DeliveryPartnerController {

	private final DeliveryPartnerDetailsService deliveryPartnerDetailsService;
	
	public DeliveryPartnerController(DeliveryPartnerDetailsService deliveryPartnerDetailsService) {
		this.deliveryPartnerDetailsService = deliveryPartnerDetailsService;
	}
	
	@PostMapping("/register")
	public ResponseEntity<DeliveryPartnerDetailsResponseDto> signIn(@RequestBody DeliveryPartnerDetailsRequestDto deliveryPartnerDetailsRequestDto){
		DeliveryPartnerDetailsResponseDto signIn = deliveryPartnerDetailsService.signIn(deliveryPartnerDetailsRequestDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(signIn);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DeliveryPartnerDetailsResponseDto> getDeliveryPartnerDetailsById(@PathVariable("id") long deliveryPartnerId){
		DeliveryPartnerDetailsResponseDto deliveryPartnerDetailsById = deliveryPartnerDetailsService.getDeliveryPartnerDetailsById(deliveryPartnerId);
		return ResponseEntity.status(HttpStatus.OK).body(deliveryPartnerDetailsById);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<DeliveryPartnerDetailsResponseDto>> getAllDeliveryPartnerDetails(){
		List<DeliveryPartnerDetailsResponseDto> allDeliveryPartnerDetails = deliveryPartnerDetailsService.getAllDeliveryPartnerDetails();
		return ResponseEntity.status(HttpStatus.OK).body(allDeliveryPartnerDetails);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<DeliveryPartnerDetailsUpdateResponseDto> updateDeliveryPartnerDetialsById(@PathVariable("id") long deliveryPartnerId, @RequestBody DeliveryPartnerDetailsUpdateRequestDto deliveryPartnerDetailsUpdateRequestDto){
		DeliveryPartnerDetailsUpdateResponseDto updateDeliveryPartnerDetailsById = deliveryPartnerDetailsService.updateDeliveryPartnerDetailsById(deliveryPartnerId, deliveryPartnerDetailsUpdateRequestDto);
		return ResponseEntity.status(HttpStatus.OK).body(updateDeliveryPartnerDetailsById);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteDeliveryPartnerById(@PathVariable("id") long deliveryPartnerId){
		deliveryPartnerDetailsService.deleteDeliveryPartnerById(deliveryPartnerId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@GetMapping("/all/available")
	public ResponseEntity<Long> getAllAvailableDeliveryPartnerDetails(){
		long availableDeliveryPartnerId = deliveryPartnerDetailsService.getAvailableDeliveryPartnerDetails();
		return ResponseEntity.status(HttpStatus.FOUND).body(availableDeliveryPartnerId);
	}
	
	
	
}
