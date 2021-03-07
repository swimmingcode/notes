package org.youyuan.relationship;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @Date: 2021/2/3 22:28
 */
public class Demo {

    public static void main(String[] args) {
        Children children = new Children(1,2,"zs");
        int id = children.getId();
        String name = children.getName();
        System.out.println(children);
    }

}
