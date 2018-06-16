package com.ssm.api.extension;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {

	
	/**
     * Pointcut定义切点函数,调用controller包下的方法就会调用切面方法
     */
    @Pointcut("execution(public * com.ssm.api.controller.*.*(..))")
    private void myPointcut(){}

    /**
     * 前置通知，方法调用前执行
     */
    @Before("myPointcut()")
    public void before(){
        System.out.println("Aop前置通知....");
    }
    
    /**
     * 后置通知，方法执行完后会调用
     * returnVal,切点方法执行后的返回值
     * 如果该方法没有返回值下面的 returnVal 会是null
     */
    @AfterReturning(value="myPointcut()",returning = "returnVal")
    public void AfterReturning(Object returnVal){
        System.out.println("Aop后置通知......方法返回值："+returnVal);
    }
}
