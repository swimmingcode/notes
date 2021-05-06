package org.youyuan.ratelimiter.value.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.util.concurrent.RateLimiter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Service;
import org.youyuan.ratelimiter.Constants;
import org.youyuan.ratelimiter.annotation.DoRateLimiter;
import org.youyuan.ratelimiter.value.IValveService;

import java.lang.reflect.Method;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/5/6 15:01
 */
@Service
public class RateLimiterValve implements IValveService {

    @Override
    public Object access(ProceedingJoinPoint jp, Method method, DoRateLimiter doRateLimiter, Object[] args) throws Throwable {
        // 判断是否开启
        //若无 直接放行
        if (0 == doRateLimiter.permitsPerSecond()) {
            return jp.proceed();
        }

        //类名称
        String clazzName = jp.getTarget().getClass().getName();
        //方法名称
        String methodName = method.getName();

        String key = clazzName + ":" + methodName;

        if (null == Constants.rateLimiterMap.get(key)) {
            //设置限流流量
            Constants.rateLimiterMap.put(key, RateLimiter.create(doRateLimiter.permitsPerSecond()));
        }

        RateLimiter rateLimiter = Constants.rateLimiterMap.get(key);
//        double acquire = rateLimiter.acquire();
//        double rate = rateLimiter.getRate();
//        System.out.println("acquire=" + acquire + "-----" + "rate=" + rate);

        if (rateLimiter.tryAcquire()) {
            return jp.proceed();
        }

        return JSON.parseObject(doRateLimiter.returnJson(), method.getReturnType());

    }

}
