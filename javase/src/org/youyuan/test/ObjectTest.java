package org.youyuan.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/3/4 16:06
 */
public class ObjectTest {
    public static void main(String[] args) {
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(1222);
        integers.add(56);
        integers.add(43);
        Object o = integers;
        System.out.println(o);
        ArrayList<Integer> res= (ArrayList<Integer>) o;
        System.out.println(res);
    }
}
