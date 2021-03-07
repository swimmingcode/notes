package org.youyuan.jwt.service;

import org.youyuan.jwt.utils.jwt.Token;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @Date: 2021/2/2 14:18
 */
public interface TokenService {

    /**
     * 创建Token字符串 用于系统鉴权
     *
     * @param token
     * @return
     */
    String createToken(Token token);

    /**
     * 验证Token字符串
     *
     * @param token
     * @return
     */
    Token verifyToken(String token);


    /**
     * 在浏览器当中设置Cookie
     *
     * @param token
     * @param response
     */
    void setCookie(String token, HttpServletResponse response);

    /**
     * 销毁Cookie
     */
    void logoutCookie();
}
