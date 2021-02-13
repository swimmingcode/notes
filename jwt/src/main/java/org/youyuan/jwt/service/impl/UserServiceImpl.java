package org.youyuan.jwt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.youyuan.jwt.domain.UserPO;
import org.youyuan.jwt.mapper.UserMapper;
import org.youyuan.jwt.service.TokenService;
import org.youyuan.jwt.service.UserService;
import org.youyuan.jwt.utils.common.response.ResponseCode;
import org.youyuan.jwt.utils.encryption.Md5Utils;
import org.youyuan.jwt.utils.exception.BaseException;
import org.youyuan.jwt.utils.jwt.Token;
import org.youyuan.jwt.vo.response.UserAccountVO;

import java.util.List;
import java.util.Optional;

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
    @Transactional
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

    @Override
    public UserPO
    verifyUserNameAndPassword(String username, String password) {
        QueryWrapper<UserPO> wrapper = new QueryWrapper();
        wrapper.eq("name",username);
        List<UserPO> userPOS = userMapper.selectList(wrapper);
        Optional.ofNullable(userPOS).orElseThrow(() -> new BaseException(ResponseCode.USER_Name_ERROR));
        //验证密码
        String pwd = md5Utils.encryptedMd5(password);
        if (!pwd.equals(userPOS.get(0).getPassword())) {
            throw new BaseException(ResponseCode.USER_PASSWORD_ERROR);
        }
        return userPOS.get(0);
    }

    @Override
    public void logoutAccount(String username) {
        QueryWrapper<UserPO> wrapper = new QueryWrapper();
        wrapper.eq("name",username);
        List<UserPO> userPOS = userMapper.selectList(wrapper);
        Optional.ofNullable(userPOS).orElseThrow(() -> new BaseException(ResponseCode.USER_Name_ERROR));
        //清空cookie
        tokenService.logoutCookie();
    }
}
