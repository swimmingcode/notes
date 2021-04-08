package org.youyuan.test;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/4/8 15:34
 */
class HelloB extends HelloA {
    public HelloB() {
        System.out.println("HelloB");
    }
    { System.out.println("I'm B class");}
    static { System.out.println("static B"); }
}
