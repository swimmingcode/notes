package org.youyuan.ratelimiter.test;

import com.google.common.util.concurrent.RateLimiter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/5/6 17:52
 */
public class Main {

    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final int THREAD_COUNT = 25;

    public static void main(String[] args) {
        RateLimiter rateLimiter = RateLimiter.create(5);

        Thread[] ts = new Thread[THREAD_COUNT];
        for (int i = 0; i < THREAD_COUNT; i++) {
            ts[i] = new Thread(new RateLimiterThread(rateLimiter), "RateLimiterThread-" + i);
        }

        for (int i = 0; i < THREAD_COUNT; i++) {
            ts[i].start();
        }

        for (;;) {

        }
    }
    public static class RateLimiterThread implements Runnable {

        private RateLimiter rateLimiter;

        public RateLimiterThread(RateLimiter rateLimiter) {
            this.rateLimiter = rateLimiter;
        }

        @Override
        public void run() {
            //塞的且会一直等待到获取令牌为止
            // 返回值为double型，意思是从阻塞开始到获取到令牌的等待时间，单位为秒
            double acquire = rateLimiter.acquire(1);
//            System.out.println(acquire);
            System.out.println(Thread.currentThread().getName() + "获取到了令牌，时间 = " + FORMATTER.format(new Date()));
        }

    }



}
