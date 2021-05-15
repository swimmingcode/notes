package org.youyuan.methodext;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.youyuan.methodext.annotation.DoMethodExt;

import java.lang.reflect.Method;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/5/6 17:09
 */
@Slf4j
@Aspect
@Component
public class DoMethodExtPoint {

    @Pointcut("@annotation(org.youyuan.methodext.annotation.DoMethodExt)")
    public void aopPoint() {
    }

    @Around("aopPoint()")
    public Object doRouter(ProceedingJoinPoint jp) throws Throwable {
        // 获取进入的方法
        Method method = getMethod(jp);
        DoMethodExt doMethodExt = method.getAnnotation(DoMethodExt.class);
        // 获取拦截方法
        // 如何调用？ 反射
        String methodName = doMethodExt.method();
        // 功能处理
        // 获取Controller的class
        //确定方法名称、以及参数类型
        // TODO: 2021/5/6 若 method.getParameterTypes()类型不一致怎么处理
        Method methodExt = getClass(jp).getMethod(methodName,method.getParameterTypes());
        Class<?> returnType = methodExt.getReturnType();

        // 判断方法返回类型
        if (!returnType.getName().equals("boolean")) {
            throw new RuntimeException("annotation @DoMethodExt set method：" + methodName + " returnType is not boolean");
        }
        // 拦截判断正常，继续
        // 反射  调用方法invoke
        boolean invoke = (boolean) methodExt.invoke(jp.getThis(), jp.getArgs());
        // 返回结果
        return invoke ? jp.proceed() : JSON.parseObject(doMethodExt.returnJson(), method.getReturnType());
    }

    private Method getMethod(JoinPoint jp) throws NoSuchMethodException {
        Signature sig = jp.getSignature();
        MethodSignature methodSignature = (MethodSignature) sig;
        return jp.getTarget().getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
    }

    private Class<? extends Object> getClass(JoinPoint jp) throws NoSuchMethodException {
        //
        return jp.getTarget().getClass();
    }
}
