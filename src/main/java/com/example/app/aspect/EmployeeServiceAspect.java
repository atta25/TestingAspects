package com.example.app.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class EmployeeServiceAspect {
    @Before(value = "execution(* com.example.app.service.EmployeeService.*(..)) && args(name,empId)")
    public void beforeAdvice(JoinPoint joinPoint, String name, String empId) {
        System.out.println("Before method:" + joinPoint.getSignature());

        System.out.println("Creating Employee with name - " + name + " and id - " + empId);
    }

    @After(value = "execution(* com.example.app.service.EmployeeService.*(..)) && args(name,empId)")
    public void afterAdvice(JoinPoint joinPoint, String name, String empId) {
        System.out.println("After method:" + joinPoint.getSignature());

        System.out.println("Successfully created Employee with name - " + name + " and id - " + empId);
    }

    @Around("execution(* com.example.app.service.*.*(..))")
    public Object calculateTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        long t1=System.currentTimeMillis();

        Object result = proceedingJoinPoint.proceed();

        long t2=System.currentTimeMillis();

        if (t2-t1 > 2000) {
            System.out.println("Metodo lento:" + proceedingJoinPoint.getTarget().getClass() + "." + proceedingJoinPoint.getSignature().getName() + ":" + (t2-t1));
        }

        return result;
    }
}
