package com.vphum.service.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * Created by darren.chen on 2018/3/16.
 */
public class AOPLog {

    public void exception(JoinPoint joinpoint, Exception exception) {
        String className = joinpoint.getTarget().getClass().getName();
        String methodName = joinpoint.getSignature().getName();

    }

    public void around(ProceedingJoinPoint joinPoint) throws Throwable {

    }
}
