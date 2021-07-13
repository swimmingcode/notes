package org.youyuan.ratelimiter.test;

import com.google.common.util.concurrent.RateLimiter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/5/6 18:04
 */
public class App {

    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    public static void main(String[] args) {

        RateLimiter rateLimiter = RateLimiter.create(1);

        System.out.println("获取1个令牌开始，时间为" + FORMATTER.format(new Date()));
        double cost = rateLimiter.acquire(1);
        System.out.println("获取1个令牌结束，时间为" + FORMATTER.format(new Date()) + ", 耗时" + cost + "ms");
        System.out.println("获取5个令牌开始，时间为" + FORMATTER.format(new Date()));
        cost = rateLimiter.acquire(5);
        System.out.println("获取5个令牌结束，时间为" + FORMATTER.format(new Date()) + ", 耗时" + cost + "ms");
        System.out.println("获取3个令牌开始，时间为" + FORMATTER.format(new Date()));
        cost = rateLimiter.acquire(3);
        System.out.println("获取3个令牌结束，时间为" + FORMATTER.format(new Date()) + ", 耗时" + cost + "ms");
    }

}
