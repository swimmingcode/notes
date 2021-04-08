package org.youyuan.test;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/4/8 15:30
 */
public class StringTest1 {
    public static void modify(String value) {
        value.toUpperCase();
        value += "3.0";
        System.out.println(value);
    }
    public static void main(String[] args) {
        String value = new String("SequoiaDB");
        StringTest1.modify(value);
        System.out.println(value);
    } }
