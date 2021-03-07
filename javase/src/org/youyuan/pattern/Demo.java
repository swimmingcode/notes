package org.youyuan.pattern;

import java.util.regex.Pattern;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/2/24 12:14
 */
public class Demo {
    public static void main(String[] args) {
        String pattern = "J??";
        String str = "JJ";
        boolean matches = Pattern.matches(pattern, str);
        System.out.println(matches);
    }
}
