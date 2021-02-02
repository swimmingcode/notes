package org.youyuan.jwt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.youyuan.jwt.service.TokenService;
import org.youyuan.jwt.utils.jwt.JwtUtils;
import org.youyuan.jwt.utils.jwt.Token;
import org.youyuan.jwt.utils.jwt.TokenUtils;

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
}
