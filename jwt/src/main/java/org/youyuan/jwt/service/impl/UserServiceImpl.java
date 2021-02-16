package org.youyuan.jwt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.youyuan.jwt.domain.UserPO;
import org.youyuan.jwt.mapper.UserMapper;
import org.youyuan.jwt.service.TokenService;
import org.youyuan.jwt.service.UserService;
import org.youyuan.jwt.utils.common.redis.RedisUtils;
import org.youyuan.jwt.utils.common.response.ResponseCode;
import org.youyuan.jwt.utils.encryption.Md5Utils;
import org.youyuan.jwt.utils.exception.BaseException;
import org.youyuan.jwt.utils.exception.ExceptionFactory;
import org.youyuan.jwt.utils.jwt.Token;
import org.youyuan.jwt.vo.response.UserAccountVO;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static org.youyuan.jwt.utils.common.Constant.PASSWORD_ERROR_TIMES;

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

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

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
    public UserPO verifyUserNameAndPassword(String username, String password) {
        //防止恶意登录，超过5次登录失败，锁定5分钟
        //需要为每一位用户设置一个唯一的key:username + PASSWORD_ERROR_TIMES
        //设置过期时间
        if (redisTemplate.hasKey(username + PASSWORD_ERROR_TIMES)) {
            if (Integer.parseInt(redisTemplate.opsForValue().get(username + PASSWORD_ERROR_TIMES).toString()) >= 5) {
                throw new ExceptionFactory(ResponseCode.PASSWORD_ERROR_TIMES);
            }
        }
        QueryWrapper<UserPO> wrapper = new QueryWrapper();
        wrapper.eq("name",username);
        List<UserPO> userPOS = userMapper.selectList(wrapper);
        Optional.ofNullable(userPOS).orElseThrow(() -> new BaseException(ResponseCode.USER_NAME_ERROR));
        //验证密码
        String pwd = md5Utils.encryptedMd5(password);
        if (!pwd.equals(userPOS.get(0).getPassword())) {
            //计算累计密码登录错误的次数 加加操作
            if (!redisTemplate.hasKey(username + PASSWORD_ERROR_TIMES)) {
                redisTemplate.opsForValue().set(username + PASSWORD_ERROR_TIMES,0, 5L,TimeUnit.MINUTES);
            }
            redisTemplate.opsForValue().increment(username + PASSWORD_ERROR_TIMES,1L);
            throw new ExceptionFactory(ResponseCode.USER_PASSWORD_ERROR.getCode(),
                    "密码错误，还剩下" + (5 - Integer.parseInt(redisTemplate.opsForValue().get(username + PASSWORD_ERROR_TIMES).toString())+"次机会"));
        }
        //如果有key 清零操作
        if (redisTemplate.hasKey(username + PASSWORD_ERROR_TIMES)) {
            redisTemplate.delete(username + PASSWORD_ERROR_TIMES);
        }
        return userPOS.get(0);
    }

    @Override
    public void logoutAccount(String username) {
        QueryWrapper<UserPO> wrapper = new QueryWrapper();
        wrapper.eq("name",username);
        List<UserPO> userPOS = userMapper.selectList(wrapper);
        Optional.ofNullable(userPOS).orElseThrow(() -> new BaseException(ResponseCode.USER_NAME_ERROR));
        //清空cookie
        tokenService.logoutCookie();
    }
}
