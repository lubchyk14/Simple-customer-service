package com.luv2code.demo.Aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.logging.Logger;

@Component
@Aspect
public class CRMLoggingAspect {

    //setup logger
    private Logger logger = Logger.getLogger(getClass().getName());

    //setup pointcut declaration
    @Pointcut("execution ( * com.luv2code.demo.Controllers.*.*(..))")
    private void forAllControllers(){}//match on
    // ANY class
    // ANY method with
    // ANY number of parameters
    //in Controllers package

    @Pointcut("execution ( * com.luv2code.demo.Service.*.*(..))")
    private void forAllServices(){}

    @Pointcut("execution ( * com.luv2code.demo.Repository.*.*(..))")
    private void forAllRepositories(){}

    @Pointcut("forAllControllers() || forAllServices() || forAllRepositories()")
    private void loggingPointcut(){}

    @Before("loggingPointcut()")
    public void beforeMethod(JoinPoint joinPoint){

        //display called method
        String calledMethod = joinPoint.getSignature().toShortString();
        logger.info("======> In @Before advice "+calledMethod);

        //display arguments of called method
        Object[] args = joinPoint.getArgs();
        logger.info(Arrays.toString(args)+" <===== Arguments displayed");
    }

    @AfterReturning(
            pointcut = "loggingPointcut()",
            returning = "result" )//spring will inject returned value from method
    public void afterMethodReturn(JoinPoint joinPoint,Object result){
        //display method we are returning from
        String method = joinPoint.getSignature().toShortString();
        logger.info("Inside @AfterReturning advice for method - "+method);

        //display data returned from method
        logger.info("Result is being returned from method --- >"+result.toString());
    }













}
