package org.youyuan.ratelimiter.value;

import org.aspectj.lang.ProceedingJoinPoint;
import org.youyuan.ratelimiter.annotation.DoRateLimiter;

import java.lang.reflect.Method;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/5/6 11:01
 */
public interface IValveService {

    Object access(ProceedingJoinPoint jp, Method method, DoRateLimiter doRateLimiter, Object[] args) throws Throwable;

}
