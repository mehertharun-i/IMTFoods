package com.IMTFoods.UserManagement.aspect;

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
public class UserManagementAop {
	
	@Before("execution(* com.IMTFoods.UserManagement.exception.*.*(..)) "+"&& args(exceptionMessage, ..)")
	public void logBeforeException(JoinPoint joinpoint, Exception exceptionMessage) {
		log.debug("Entered into "+joinpoint.getSignature()+" method with a Exception Class of ( "+joinpoint.getClass().getSimpleName()+" ) : {}",exceptionMessage.getMessage());
	}
	
	@AfterThrowing(pointcut ="execution(* com.IMTFoods.UserManagement.*.*.*(..))", throwing="exceptionMessage")
	public void logAfterThrowing(JoinPoint joinpoint, Exception exceptionMessage) {
		log.debug("Exception occured for "+joinpoint.getSignature()+" , Exception "+joinpoint.getClass().getSimpleName()+" : {}",exceptionMessage.getMessage());
	}
	
	@Around("execution(* com.IMTFoods.UserManagement.*.*.*(..)) " + "&& !within(com.IMTFoods.UserManagement.exception.*) " + "&& args(request, ..)")
	public Object logAround(ProceedingJoinPoint joinpoint, Object request) throws Throwable {
		log.debug("Entered into "+joinpoint.getSignature()+" method with request DataType of ( "+request.getClass().getSimpleName()+" ) : {}", request);
		
		Object proceed = joinpoint.proceed();
		
		log.debug("Exited from "+joinpoint.getSignature()+" method and returning a response as : {}", proceed);
		return proceed;
	}
}
