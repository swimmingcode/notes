package org.youyuan.test;

import java.util.Random;

/**
 * @Describe:test
 * @Author: youjiancheng
 * @Date: 2021/1/27 16:58
 */
public class Test {
    public static void main(String[] args) {
//        Class<? extends Test> aClass = new Test().getClass();
//        ClassLoader classLoader = aClass.getClassLoader();
//        System.out.println(classLoader.getParent());
//        Object o = new Object();
//        System.out.println(o.toString());

        Random random = new Random();
        String result="";
        for (int i=0;i<6;i++)
        {
            result+=random.nextInt(10);
        }
        System.out.println(result);
    }
}
