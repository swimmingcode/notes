package org.youyuan.jwt.utils.common;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/2/27 11:34
 */
public class DateUtils {

    static SimpleDateFormat simpleDateFormatTime = new SimpleDateFormat("YYYY-MM-dd HH:MM:SS");

    static SimpleDateFormat simpleDateFormatDay = new SimpleDateFormat("YYYY-MM-dd");

    static SimpleDateFormat simpleDateFormatImage = new SimpleDateFormat("/YYYY/MM/dd/");

    /**
     * 时间格式化（精确到秒）
     *
     * @param date
     * @return
     */
    public static String dateFormatTime(Date date) {
        String format = simpleDateFormatTime.format(date);
        return format;
    }

    /**
     * 时间格式化（精确到天）
     * @param date
     * @return
     */
    public static String dateFormatDay(Date date) {
        String format = simpleDateFormatDay.format(date);
        return format;
    }

    /**
     * 将日期用于存放图片路径
     *
     * @param date
     * @return
     */
    public static String dateImageFolder(Date date) {
        String format = simpleDateFormatImage.format(date);
        return format;
    }

    /**
     * 获取前n日的日期
     *
     * @param days
     * @return
     */
    public static String getBeforeDayFormatDay(int days) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE,-days);
        Date time= cal.getTime();
        return simpleDateFormatDay.format(time);
    }
}
