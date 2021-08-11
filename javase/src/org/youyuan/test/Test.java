package org.youyuan.test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Describe:test
 * @Author: youjiancheng
 * @Date: 2021/1/27 16:58
 */
public class Test {

    public static void main(String[] args) throws CloneNotSupportedException {
//        StringBuilder str = new StringBuilder("000");
//        testString(str);
//        System.out.println(str);
        /***********************************/
//        User user = new User();
//        user.setName("yjc");
//        setUser(user);
//        System.out.println(user);
        AtomicInteger  integer = new AtomicInteger(1000);
        changeInteger(integer);
        System.out.println(integer);
    }

    private static void changeInteger(AtomicInteger integer) {
        integer.set(1);;
    }

    private static void setUser(User user) throws CloneNotSupportedException {
//        User user1 = new User();
//        user1.setAge(1);
//        user1.setName("zs");
//        System.out.println("user="+user);
//        user = user1;
//
//        //这是什么拷贝
//        //user = ((User) user1.clone());
//        System.out.println("user="+user);
        /********************************/
        user.setName("youyuanccode");
        user.setAge(23);
        System.out.println(user);

        User user1 = new User();
        user1.setAge(1);
        user1.setName("zs");
        user = user1;
        System.out.println(user);
    }
    private static void testString(StringBuilder string) {
        string.delete(0,string.length()).append("hello");
        System.out.println(string);
    }
}
