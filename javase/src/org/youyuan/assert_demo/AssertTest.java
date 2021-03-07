package org.youyuan.assert_demo;

/**
 * @Author youjiancheng
 * @Date 2021/1/21 12:15
 */
public class AssertTest {
    public static void main(String[] args) {
        //JVM默认关闭断言指令，即遇到assert语句就自动忽略了，不执行。
        //要执行assert语句，必须给Java虚拟机传递-enableassertions（可简写为-ea）参数启用断言
        int x  = -1;
        assert x > 0;
        System.out.println(x);
    }
}
