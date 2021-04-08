package org.youyuan.test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/4/8 15:32
 */
public class MapTest1 {
    public static void main(String[] args) {
        Map<MyKey, Integer> myMap = new HashMap();
        myMap.put(new MyKey(1), 1);
        myMap.put(new MyKey(2), 2);
        Integer value = myMap.get(new MyKey(1));
        System.out.println("value=" + value);
    } }
