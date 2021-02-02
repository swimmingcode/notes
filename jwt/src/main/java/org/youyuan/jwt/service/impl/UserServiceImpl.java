package org.youyuan.jwt.service.impl;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.youyuan.jwt.domain.UserPO;
import org.youyuan.jwt.mapper.UserMapper;
import org.youyuan.jwt.service.TokenService;
import org.youyuan.jwt.service.UserService;
import org.youyuan.jwt.utils.encryption.Md5Utils;
import org.youyuan.jwt.utils.jwt.Token;
import org.youyuan.jwt.vo.response.UserAccountVO;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @Date: 2021/2/2 14:59
 */
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    TokenService tokenService;

    @Autowired
    UserMapper userMapper;

    @Autowired
    Md5Utils md5Utils;

    @Override
    public String login(Token token) {
        String tokenRes = tokenService.createToken(token);
        return tokenRes;
    }

    @Override
    public UserAccountVO createAccount() {
        //生成账号
        String userName = "YJ" + RandomStringUtils.randomAlphabetic(4);
        String password = RandomStringUtils.randomAlphabetic(6);
        //md5加密
        String pwd = md5Utils.encryptedMd5(password);
        userMapper.insert(UserPO.builder().name(userName).password(pwd).build());
        return UserAccountVO.builder()
                .username(userName)
                .password(password)
                .build();
    }
}