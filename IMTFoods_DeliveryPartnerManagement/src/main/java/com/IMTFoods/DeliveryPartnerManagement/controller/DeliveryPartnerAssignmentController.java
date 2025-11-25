package com.IMTFoods.DeliveryPartnerManagement.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.IMTFoods.DeliveryPartnerManagement.dto.DeliveryPartnerAssignmentRequestDto;
import com.IMTFoods.DeliveryPartnerManagement.dto.DeliveryPartnerAssignmentResponseDto;
import com.IMTFoods.DeliveryPartnerManagement.service.DeliveryPartnerAssignmentService;

@RestController
@RequestMapping("/deliveryassignment")
public class DeliveryPartnerAssignmentController {

	private final DeliveryPartnerAssignmentService deliveryAssignmentService;
	
	public DeliveryPartnerAssignmentController(DeliveryPartnerAssignmentService deliveryAssignmentService) {
		this.deliveryAssignmentService = deliveryAssignmentService;
	}
	
	@PostMapping("/assign")
	public ResponseEntity<DeliveryPartnerAssignmentResponseDto> createDeliveryAssignment(@RequestBody DeliveryPartnerAssignmentRequestDto deliveryPartnerAssignmentRequestDto){
		DeliveryPartnerAssignmentResponseDto deliveryPartnerAssignmentResponseDto = deliveryAssignmentService.createDeliveryAssignment(deliveryPartnerAssignmentRequestDto);		
		return ResponseEntity.status(HttpStatus.CREATED).body(deliveryPartnerAssignmentResponseDto);
	}
	
	@PostMapping("/assign/all")
	public ResponseEntity<List<DeliveryPartnerAssignmentResponseDto>> createAllDeliveryAssignment(@RequestBody List<DeliveryPartnerAssignmentRequestDto> deliveryPartnerAssignmentRequestDto){
		List<DeliveryPartnerAssignmentResponseDto> listOfDeliveryPartnerAssignmentResponseDto = deliveryAssignmentService.createAllDeliveryAssignment(deliveryPartnerAssignmentRequestDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(listOfDeliveryPartnerAssignmentResponseDto);
	}
	
	@GetMapping("/assign/find/{id}")
	public ResponseEntity<DeliveryPartnerAssignmentResponseDto> getDeliveryPartnerAssignmentById(@PathVariable("id") long deliveryAssignmentId){
		DeliveryPartnerAssignmentResponseDto deliveryPartnerAssignmentResponseDto = deliveryAssignmentService.getDeliveryPartnerAssignmentById(deliveryAssignmentId);
		return ResponseEntity.status(HttpStatus.FOUND).body(deliveryPartnerAssignmentResponseDto);
	}
	
	@GetMapping("/assign/find/all")
	public ResponseEntity<List<DeliveryPartnerAssignmentResponseDto>> getAllDeliveryPartnerAssignment(){
		List<DeliveryPartnerAssignmentResponseDto> listOfAllDeliveryPartnerAssignment = deliveryAssignmentService.getAllDeliveryPartnerAssignment();
		return ResponseEntity.status(HttpStatus.FOUND).body(listOfAllDeliveryPartnerAssignment);
	}
	
	@PutMapping("/assign/update/{id}")
	public ResponseEntity<DeliveryPartnerAssignmentResponseDto> updateDeliveryPartnerAssignmentStatus(@PathVariable("id") long deliveryAssignmentId, @RequestBody DeliveryPartnerAssignmentRequestDto deliveryPartnerAssignmentRequestDto){
		return null;
	}
	
	
}
