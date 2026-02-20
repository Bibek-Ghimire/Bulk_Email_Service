package com.EmailService.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class AspectImpl {

    /*
      Here @Before is the Advice and the condition inside the bracket is PointCut
     */
    @Before("execution(* com.EmailService.services.UserService.*(..))")
    public void beforeMethod(){
        System.out.println("This is a before method");

    }


    // Here ProceedingJoinPoint(implements JoinPoint) is the JoinPoint object
    @Around("execution(* com.EmailService.services.EmailService.*(..))")
    public Object aroundMethhod(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Before around method execution");

        // used to proceed and execute the method and return the data
        Object object = joinPoint.proceed();

        System.out.println("After around method execution");

        return object;

    }

    @AfterReturning(
            pointcut = "execution(* com.EmailService.services.EmailService.*(..))",
            returning = "result"
    )
    public void logReturn(JoinPoint joinPoint, Object result) {
        System.out.println("Method returned: " + result);
    }


    /*
        Similar to above there are other annotations like

        @After -> runs after the method is executed
        @AfterReturning -> executes only when the method is executed successfully without exception
        @AfterThrowing -> executes only when the method throws an exception

    */

}
