package org.youyuan.threadlocal;

import java.util.function.Supplier;

/**
 * @Author youjiancheng
 * @Date 2021/1/21 16:02
 */
public class ThreadLocalTest {

    public static final ThreadLocal<String> threadLocal= ThreadLocal.withInitial(new Supplier<String>() {
        @Override
        public String get() {
            return Thread.currentThread().getName();
        }
    });

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                synchronized (ThreadLocalTest.class) {
                    System.out.println(threadLocal.get());
                }
            },"Thread"+i).start();
        }
    }
}
