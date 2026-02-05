package com.IMTFoods.FoodOrderManagement.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class FoodOrderManagementAOP {
	
	@Before("execution(* com.IMTFoods.FoodOrderManagement.exception.*.*(..) "+"&& args(exceptionMessage, ..)")
	public void FoodOrderManagementBeforeLoggerForExceptionOnly(JoinPoint joinPoint, Exception exceptionMessage) {
		log.debug("Entered into "+joinPoint.getSignature()+", method with request datatype of ("+exceptionMessage.getClass().getSimpleName()+" ) : {}", exceptionMessage.getMessage());
	}
	
	@AfterThrowing(pointcut = "excution(* com.IMTFoods.FoodOrderManagement.*.*.*(..)", throwing = "exceptionMessage")
	public void FoodOrderManagementAfterThrowingLogger(JoinPoint joinPoint, Exception exceptionMessage) {
		log.debug("Exception occured at "+joinPoint.getSignature()+" , Exception "+joinPoint.getClass().getSimpleName()+" : {}", exceptionMessage.getMessage());
	}
	
	@Around("execution(* com.IMTFoods.FoodOrderManagement.*.*.*(..))"+" && !within(com.IMTFoods.FoodOrderManagement.exception.*)"+" && args(request, ..)")
	public Object FoodOrderManagementAroundLogger(ProceedingJoinPoint proceedingJoinPoint, Object request) throws Throwable {
		log.debug("Entered into "+proceedingJoinPoint.getSignature()+" method with request datatype of ("+request.getClass().getSimpleName()+") : {}", request);
		
		Object proceed = proceedingJoinPoint.proceed();
		
		log.debug("Exited from "+proceedingJoinPoint.getSignature()+" method and returning with a response as : {}", proceed);
		return proceed;
	}

}
