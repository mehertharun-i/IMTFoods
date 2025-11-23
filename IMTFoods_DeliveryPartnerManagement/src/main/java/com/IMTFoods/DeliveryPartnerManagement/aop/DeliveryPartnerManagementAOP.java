package com.IMTFoods.DeliveryPartnerManagement.aop;

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
public class DeliveryPartnerManagementAOP {

	@Before("execution(* com.IMTFoods.DeliveryPartnerManagement.exception.*.*(..)) "+"&& args(exceptionMessage, ..)")
	public void DeliveryPartnerBeforeLoggerForExceptionOnly(JoinPoint joinPoint, Exception exceptionMessage) {
		log.debug("Entered into "+joinPoint.getSignature()+" method with a Exception Class of ("+joinPoint.getClass().getSimpleName()+") : {}",exceptionMessage.getMessage());
	}
	
	
	@AfterThrowing(pointcut = "execution(* com.IMTFoods.DeliveryPartnerManagement.*.*.*(..)", throwing = "exceptionMessage")
	public void DeliveryPartnerExceptionLogger(JoinPoint joinPoint, Exception exceptionMessage) {
		log.debug("Exception occured at "+joinPoint.getSignature()+" , Exception "+joinPoint.getClass().getSimpleName()+" : {}",exceptionMessage.getMessage());
	}
	
	
	@Around("execution(* com.IMTFoods.DeliveryPartnerManagement.*.*.*(..))"+" && !within(com.IMTFoods.DeliveryPartnerManagement.exception.*)"+" && args(request, ..)")
	public Object DeliveryPartnerAroundLogger(ProceedingJoinPoint proceedingJoinPoint, Object request) throws Throwable {
		log.debug("Entered into "+proceedingJoinPoint.getSignature()+" method with request datatype of ("+request.getClass().getSimpleName()+") : {} ",request);
		
		Object proceed = proceedingJoinPoint.proceed();
		
		log.debug("Exited from "+proceedingJoinPoint.getSignature()+" method and returning a response as : {}", proceed);
		return proceed;
	}
	
}
