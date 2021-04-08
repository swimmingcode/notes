package org.youyuan.test;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/4/8 15:35
 */
public class TestGetValue {
    public static int getValue(int i) {
        int result = 0;
        switch (i) {
            case 1:
                result = result + i;
            case 2:
                result = result + i * 2;
            case 3:
                result = result + i * 3;
        }
        return result;
    }
    public static void main(String[] args) {
        System.out.println(getValue(2));
    } }
