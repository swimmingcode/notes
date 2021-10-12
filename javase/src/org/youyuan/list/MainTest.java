package org.youyuan.list;

import java.util.ArrayList;
import java.util.List;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/10/11 16:08
 */
public class MainTest {
    public static void main(String[] args) {
        TestList testList = new TestList();
        ArrayList list = new ArrayList();
        list.add(1);
        list.add(2);
        testList.setList(list);


        List list1 = testList.getList();

        list1.add(4);
        list.add(5);

        System.out.println("testList= "+testList);
        System.out.println("list1= "+list1);
    }
}
