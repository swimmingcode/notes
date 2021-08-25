package org.youyuan.callback;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/8/25 15:08
 */
public class Main {
    public static void main(String[] args) {
        int i = 1;
        Play play = new Play() {
            @Override
            public int test() {
                return 3;
            }
        };
        Play p = () -> {
            System.out.println(1);
            return 0;
        };
        int test = play.test();
        System.out.println(i);
    }
}
