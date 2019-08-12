package com.holelin.aspects;

import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserAspect {
    private Logger mLogger = LoggerFactory.getLogger(getClass());
    @Pointcut("execution(* com.holelin.service.UserServiceImpl.*(..))")
    private void pointCut() {
    }
    @Before("pointCut()")
    public void before() {
        mLogger.info("before");
    }
    @After("pointCut()")
    public void after() {

        mLogger.info("after");
    }
    @AfterReturning("pointCut()")
    public void afterReturning() {

        mLogger.info("AfterReturnings");
    }
    @AfterThrowing("pointCut()")
    public void afterThrowing(){

        mLogger.info("AfterThrowing");
    }
}
