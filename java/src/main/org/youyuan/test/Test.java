package org.youyuan.test;

/**
 * @Describe:test
 * @Author: youjiancheng
 * @Date: 2021/1/27 16:58
 */
public class Test {
    public static void main(String[] args) {
        Class<? extends Test> aClass = new Test().getClass();
        ClassLoader classLoader = aClass.getClassLoader();
        System.out.println(classLoader.getParent());
    }
}
