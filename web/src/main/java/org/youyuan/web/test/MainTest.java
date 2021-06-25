package org.youyuan.web.test;

import org.springframework.context.annotation.Primary;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/6/15 16:38
 */
public class MainTest {

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public MainTest(String name) {
        this.name = name;
    }

    public static int getX(){
        int a = 1;
        int b = 2;
        return a+b;
    }

    @Override
    public String toString() {
        return "MainTest{" +
                "name='" + name + '\'' +
                '}';
    }

    static public void print() {
         System.out.println("aaa");
        System.out.println("bbb");
        System.out.println("ccc");
    }

    static public void stream1() {
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        List<Integer> collect = integers.stream()
                .map(integer -> integer * 2).collect(Collectors.toList());

    }

    public static void main(String[] args) {
//        print();
//        getX();
//        MainTest mainTest = new MainTest("youyuancode");
//        mainTest.setName("zs");
//        System.out.println(mainTest);
//        for (int i = 0; i < 10; i++) {
//            System.out.println(i);
//        }
        stream1();
    }
}
