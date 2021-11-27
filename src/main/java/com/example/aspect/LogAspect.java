package com.example.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LogAspect {

	@Before("execution(* *..*.*UserService.*(..)))")
	public void startLog(JoinPoint jp) {
		log.info("START:"+jp.getSignature());
	}

	@After("execution(* *..*.*UserService.*(..)))")
	public void endLog(JoinPoint jp) {
		log.info("END:"+jp.getSignature());
	}

	@Around("@within(org.springframework.stereotype.Controller)")
	public Object startLog(ProceedingJoinPoint jp) throws Throwable {
		log.info("START:"+jp.getSignature());
		try {
			Object result = jp.proceed();
			log.info("END:"+jp.getSignature());
			return result;
		}catch(Exception e){
			log.error("ERROR:"+jp.getSignature());
			throw e;
		}
	}

}
