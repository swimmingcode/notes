package org.youyuan.stream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @Date: 2021/2/4 18:17
 */
public class NonNullDemo {

    private Integer id;

    public void test() {
        test1();
    }

    public static void test1(){
//        test();
    }
    public static void main(String[] args) {

        List<String> ids = new ArrayList<>();

        List<String> collect = ids.stream().collect(Collectors.toList());

        HashMap<Object, Object> objectObjectHashMap = new HashMap<>(11);
    }

}
