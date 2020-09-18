package com.bouncer77.springbootapp1.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 18.09.2020
 */

@Aspect
public class ControllersAspects {



    /*@SuppressWarnings("SpringAopErrorsInspection")*/
    /*@Pointcut("call(public * com.bouncer77.springbootapp1.controller..*.*(..))")
    public void anyControllersMethod() {}

    @Pointcut("call(public !void com.bouncer77.springbootapp1.controller..*.*(..))")
    public void notVoidControllersMethod() {}

    @Before("anyControllersMethod()")
    public void logMethod(JoinPoint joinPoint) {
        Logger log = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        StringBuilder message = new StringBuilder(joinPoint.getSignature().getName());
        Object[] args = joinPoint.getArgs();
        if (args.length > 0) {
            message.append(" ").append(Arrays.toString(args));
        }
        log.info(message.toString());
    }*/

    /*@AfterThrowing(value = "anyControllersMethod()", throwing = "ex")
    public void logExceprion(Exception ex, JoinPoint joinPoint) {
        Logger log = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        log.info("URL:{}", WebDriverFactory.getDriver().getCurrentUrl());
        log.info("Title: {}", WebDriverFactory.getDriver().getTitle());
    }

    @AfterReturning(value = "notVoidControllersMethod()", returning = "ret")
    public void logReturnMethod(Object ret, JoinPoint joinPoint) {
        Logger log = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        String message = joinPoint.getSignature().getName() + " returned: " + ret;
        log.info(message);
    }*/

    /*
    * В Cucumber фреймворке для BDD автоматизированных тестов
    * BDD - понятный человеку сценарии для тестов
    *
    * Объектная модель билдится на этапе инициализации -
    * но можно внедриться туда объектами
    * */
}
