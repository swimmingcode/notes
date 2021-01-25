package org.youyuan.number;

/**
 * @Describe
 * @Author youjiancheng
 * @Date 2021/1/25 16:24
 */

import java.text.*;

/**
 * 在这个例子中设置了数字的格式，使用像"####.000"的符号。
 * 这个模式意味着在小数点前有四个数字，如果不够就空着，小数点后有三位数字，不足用0补齐。
 */
public class FormatDemo {

    public static void main(String[] args) {
        FormatDemo fd = new FormatDemo();
        fd.format1("0000", 123.123);
        fd.format1("##.####", 123.123);
        fd.format1("###.###\u2030", 0.00123);
        fd.format1("0.000E0000", 123.15415);
    }

    public void format1(String pattern, double value) {
        DecimalFormat df = null; //声明一个DecimalFormat对象
        df = new DecimalFormat(pattern);//传入模板
        String str = df.format(value);//格式话数字
        System.out.println("使用" + pattern + "来格式化数字：" + value + "结果为：" + str);
    }
}


