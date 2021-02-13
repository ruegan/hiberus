package com.heroesApi.utils.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TemporizadorAdvice {
	
	Logger logger=LoggerFactory.getLogger(TemporizadorAdvice.class);
	
	@Around("@annotation(com.heroesApi.utils.annotation.Temporizado)")
	public Object trackTime(ProceedingJoinPoint pjp) throws Throwable {
		long inicio=System.currentTimeMillis();
		Object obj=pjp.proceed();
		long fin=System.currentTimeMillis();
		logger.info("Nombre metodo"+pjp.getSignature()+" tiempo ejecucion: "+(fin-inicio));
		return obj;
	}

}
