package org.mudoker.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {
	private static final Logger logger = LoggerFactory.getLogger(LoggerAspect.class);

	@Before("execution(* org.mudoker..*(..))")
	public void logBefore(JoinPoint joinPoint) {
		logger.info("Executing: {}", joinPoint.getSignature().getName());
	}

	@AfterReturning(pointcut = "execution(* org.mudoker..*(..))", returning = "result")
	public void logAfterReturning(JoinPoint joinPoint, Object result) {
		if (result instanceof ResponseEntity <?> response) {
			logger.info("Executed: {} - Status: {}", joinPoint.getSignature().getName(), response.getStatusCode());
		} else {
			logger.info("Executed: {} - Success", joinPoint.getSignature().getName());
		}
	}

	@AfterThrowing(pointcut = "execution(* org.mudoker..*(..))", throwing = "error")
	public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
		logger.error("Exception in: {} - Error: {}", joinPoint.getSignature().getName(), error.getMessage());
	}
}
