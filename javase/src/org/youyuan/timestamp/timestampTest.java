package org.youyuan.timestamp;

import java.util.Date;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/10/11 23:24
 */
public class timestampTest {
    public static void main(String[] args) throws InterruptedException {
        while (true) {
            long l = System.currentTimeMillis();
            //System.out.println(l);
            //1633965951817

            Date date = new Date();
            //System.out.println(date);
            date.setTime(1633966560000L);

            long time = date.getTime();
            //System.out.println(time);

            if (Math.abs(time - l) < 1000) {
                System.out.println("11111111111111111");
                break;
            }

            Thread.sleep(10);
        }




    }
}
