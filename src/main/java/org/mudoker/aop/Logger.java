package org.mudoker.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Logger {
	@Before("execution(* org.mudoker..*(..))")
	public void logBefore(JoinPoint joinPoint) {
		System.out.println("Executing: " + joinPoint.getSignature().getName());
	}
}
