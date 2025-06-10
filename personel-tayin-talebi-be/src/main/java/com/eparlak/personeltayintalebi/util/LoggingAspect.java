package com.eparlak.personeltayintalebi.util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LoggingAspect {


    // logs klasörü altında application.log dosyası oluşturuluyor.
    
    @Around("execution(* com.eparlak.personeltayintalebi.controller.*.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        
        log.info("İŞLEM BAŞLADI: {}.{}", className, methodName);
        
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        
        log.info("İŞLEM BİTTİ: {}.{} - Süre: {}ms", className, methodName, (endTime - startTime));
        
        return result;
    }
}