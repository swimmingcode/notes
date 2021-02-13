package org.youyuan.jwt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.youyuan.jwt.service.TokenService;
import org.youyuan.jwt.utils.common.redis.RedisUtils;
import org.youyuan.jwt.utils.common.web.GlobalHttpUtils;
import org.youyuan.jwt.utils.jwt.JwtUtils;
import org.youyuan.jwt.utils.jwt.Token;
import org.youyuan.jwt.utils.jwt.TokenUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;

import static org.youyuan.jwt.utils.common.Constant.BLACK_TOKEN__LIST;
import static org.youyuan.jwt.utils.common.Constant.TOKEN;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @Date: 2021/2/2 14:19
 */
@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    TokenUtils tokenUtils;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    @Autowired
    RedisUtils redisUtils;


    @Override
    public String createToken(Token token) {
        String toTokenString = tokenUtils.toTokenString(token);
        //生成JWT
        String jwt = jwtUtils.createJWT(toTokenString);
        return jwt;
    }

    @Override
    public Token verifyToken(String token) {
        //解析JWT
        String parseJWT = jwtUtils.parseJWT(token);
        //获取Token
        Token tokenRes = tokenUtils.toTokenEntity(parseJWT);
        return tokenRes;
    }


    @Override
    public void setCookie(String token, HttpServletResponse response) {
        Cookie cookie = new Cookie("token",token);
        cookie.setMaxAge(3600);
        //设置路径，这个路径即该工程下都可以访问该cookie
        // 如果不设置路径，那么只有设置该cookie路径及其子路径可以访问
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    /**
     *  销毁Cookie
     */
    @Override
    public void logoutCookie() {
        HttpServletRequest httpServletRequest = GlobalHttpUtils.getGlobalHttpRequest();
        HttpServletResponse globalHttpResponse = GlobalHttpUtils.getGlobalHttpResponse();
        Cookie[] cookies = httpServletRequest.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(TOKEN)) {
                //将token设置为黑名单 存入Redis当中
                List<String> value = new ArrayList<>();
                value.add(cookie.getValue());
                if (!redisTemplate.hasKey(BLACK_TOKEN__LIST)) {
                    redisTemplate.opsForList().set(BLACK_TOKEN__LIST,60 * 60 * 24L,new ArrayList<>());
                }
                redisUtils.opsForList(BLACK_TOKEN__LIST,value);
                cookie.setMaxAge(0);
                cookie.setPath("/");  //路径一定要写上，不然销毁不了
                globalHttpResponse.addCookie(cookie);
                break;
            }
        }
    }
}
