package org.youyuan.base64;

import sun.misc.BASE64Decoder;

import java.io.IOException;

/**
 * @Describe
 * @Author youjiancheng
 * @Date 2021/1/25 22:10
 */
public class Base64 {
    public static void main(String[] args) throws IOException {
        BASE64Decoder base64Decoder = new BASE64Decoder();
        String gb2312 = new String(base64Decoder.decodeBuffer("yf3WsLzT0L0="), "GB2312");
        System.out.println(gb2312);
    }
}
