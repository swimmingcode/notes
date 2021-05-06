package org.youyuan.hystrix.value;

import org.aspectj.lang.ProceedingJoinPoint;
import org.youyuan.hystrix.anno.DoHystrix;

import java.lang.reflect.Method;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/5/6 11:01
 */
public interface IValveService {

    Object access(ProceedingJoinPoint jp, Method method, DoHystrix doHystrix, Object[] args) throws Throwable;
}
