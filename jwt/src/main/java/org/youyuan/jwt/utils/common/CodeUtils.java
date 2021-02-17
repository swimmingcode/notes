package org.youyuan.jwt.utils.common;

import java.util.Random;

/**
 * @Describe: 生成邮箱验证码
 * @Author: youjiancheng
 * @date 2021/2/17 11:44
 */
public class CodeUtils {

    public static Integer randomEmailCode() {
        Random random = new Random();
        String result = "";
        for (int i = 0;i < 6;i++) {
            result+=random.nextInt(10);
        }
        return Integer.parseInt(result);
    }
}
