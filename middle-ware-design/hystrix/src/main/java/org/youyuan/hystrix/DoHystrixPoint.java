package org.youyuan.hystrix;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.youyuan.hystrix.anno.DoHystrix;
import org.youyuan.hystrix.value.IValveService;
import org.youyuan.hystrix.value.impl.HystrixValveImpl;

import java.lang.reflect.Method;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/5/6 13:09
 */
@Aspect
@Component
public class DoHystrixPoint {

    @Autowired
    private IValveService valveService;

    @Pointcut("@annotation(org.youyuan.hystrix.anno.DoHystrix)")
    public void aopPoint() {
    }

    //    @Around("aopPoint() && @annotation(doGovern)")
    @Around("aopPoint() && @annotation(doGovern)")//注解作为参数传入
    public Object doRouter(ProceedingJoinPoint jp, DoHystrix doGovern) throws Throwable {
        return valveService.access(jp, getMethod(jp), doGovern, jp.getArgs());
    }

    private Method getMethod(JoinPoint jp) throws NoSuchMethodException {
        Signature sig = jp.getSignature();
        MethodSignature methodSignature = (MethodSignature) sig;
        return jp.getTarget().getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
    }

}
