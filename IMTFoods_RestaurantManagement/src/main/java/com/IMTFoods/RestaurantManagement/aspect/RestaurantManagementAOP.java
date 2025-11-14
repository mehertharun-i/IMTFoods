package com.IMTFoods.RestaurantManagement.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class RestaurantManagementAOP {
	
	@Before("execution(* com.IMTFoods.RestaurantManagement.exception.*.*(..)) "+"&& args(exceptionMessage, ..)")
	public void logBeforeException(JoinPoint joinPoint, Exception exceptionMessage) {
		log.debug("Entered into "+joinPoint.getSignature()+" method with a Exception class of ("+joinPoint.getClass().getSimpleName()+") : {}", exceptionMessage.getMessage());
	}
	
	@AfterThrowing(pointcut = "execution(* com.IMTFoods.RestaurantManagement.*.*.*(..))", throwing = "exceptionMessage")
	public void logAfterThrowing(JoinPoint joinPoint, Exception exceptionMessage) {
		log.debug("Exception occured for "+joinPoint.getSignature()+" , Exception "+joinPoint.getClass().getSimpleName()+" : {}", exceptionMessage.getMessage());
	}
	
	@Around("execution(* com.IMTFoods.RestaurantManagement.*.*.*(..)) "+"&& !within(com.IMTFoods.RestaurantManagement.exception.*) "+"&& args(request, ..)")
	public Object LogAroundRestaurantManagement(ProceedingJoinPoint proceedingJoinPoint, Object request) throws Throwable {
		log.debug("Entered into "+proceedingJoinPoint.getSignature()+" method with a request Data Type of ("+request.getClass().getSimpleName()+") : {}", request);
		
		Object proceed = proceedingJoinPoint.proceed();
		
		log.debug("Exited from "+proceedingJoinPoint.getSignature()+" method and returning a response as : {}", proceed);
		return proceed;
	}

}
