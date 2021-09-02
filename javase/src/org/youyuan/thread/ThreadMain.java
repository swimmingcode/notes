package org.youyuan.thread;

import java.util.concurrent.TimeUnit;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/9/2 10:44
 */
public class ThreadMain {
    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println("结束");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        //设置为守护进程 main线程结束 JVM推出
        //如果为false main线程推出    Java虚拟机会等到thread线程执行结束才会退出
        //main线程只是一个程序的入口 线程之间并不会相互影响  由CPU调控
        // 主要进程不结束  线程就可以执行
        thread.setDaemon(true);
        thread.start();
        System.out.println(1);
    }
}
