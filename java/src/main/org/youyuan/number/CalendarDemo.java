package org.youyuan.number;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @Describe: 获取当天的某个时间点
 * @Author: youjiancheng
 * @Date: 2021/2/5 17:27
 */
public class CalendarDemo {
    public static void main(String[] args) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        System.out.println(simpleDateFormat.format(calendar.getTime()));
        //2021-00-05 12:00:00
    }
}
