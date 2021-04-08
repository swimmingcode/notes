package org.youyuan.test;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/4/8 15:31
 */
public class StringTest2 {
    public static void modify(StringBuilder value) {
        value.append("3.0");
        System.out.println(value);
    }
    public static void main(String[] args) {
        StringBuilder value = new StringBuilder("SequoiaDB");
        StringTest2.modify(value);
        System.out.println(value);
    } }
