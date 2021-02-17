package org.youyuan.jwt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.youyuan.jwt.domain.UserPO;
import org.youyuan.jwt.mapper.UserMapper;
import org.youyuan.jwt.service.TokenService;
import org.youyuan.jwt.service.UserService;
import org.youyuan.jwt.utils.common.CodeUtils;
import org.youyuan.jwt.utils.common.EmailUtils;
import org.youyuan.jwt.utils.common.redis.RedisUtils;
import org.youyuan.jwt.utils.common.response.ResponseCode;
import org.youyuan.jwt.utils.diyenum.EmailCodeType;
import org.youyuan.jwt.utils.encryption.Md5Utils;
import org.youyuan.jwt.utils.exception.BaseException;
import org.youyuan.jwt.utils.exception.ExceptionFactory;
import org.youyuan.jwt.utils.jwt.Token;
import org.youyuan.jwt.vo.request.BindEmailVO;
import org.youyuan.jwt.vo.request.EmailCodeVO;
import org.youyuan.jwt.vo.request.UpdatePasswordByCodeVO;
import org.youyuan.jwt.vo.request.UpdatePasswordByOldPwdVO;
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
@Slf4j
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

    @Autowired
    EmailUtils emailUtils;

    @Autowired
    TemplateEngine templateEngine;

    @Qualifier("taskExecutor")
    @Autowired
    ThreadPoolTaskExecutor threadPoolTaskExecutor;

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
        //判断登录的次数
//        if (redisTemplate.hasKey(userAccountVO.getUsername()) && Integer.parseInt(redisTemplate.opsForValue().get(userAccountVO.getUsername()).toString()) > 0) {
//
//        }
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

    @Override
    public void updateAccountUserName(String usernameOld, String usernameNew) {
        QueryWrapper<UserPO> wrapper = new QueryWrapper();
        wrapper.eq("name",usernameOld);
        List<UserPO> userPOS = userMapper.selectList(wrapper);
        Optional.ofNullable(userPOS).orElseThrow(() -> new BaseException(ResponseCode.USER_NAME_ERROR));
        UserPO userPO = userPOS.get(0);
        userPO.setName(usernameNew);
        userMapper.updateById(userPO);
    }

    @Transactional
    @Override
    public void getEmailCode(EmailCodeVO emailCodeVO) {
        long x = System.currentTimeMillis();
        //生成随机验证码
        Integer code = CodeUtils.randomEmailCode();
        //存入Redis,设置过期时间为5分钟
        redisTemplate.opsForValue().set(emailCodeVO.getEmailCodeType().name() + emailCodeVO.getName(),code,5L,TimeUnit.MINUTES);
        //发送邮箱
        Context context = new Context();
        context.setVariable("code",code);
        //email位templates下的邮件模板
        String mail = templateEngine.process("email", context);
        //异步处理
        threadPoolTaskExecutor.execute(()->{
            emailUtils.sendHtmlMail("1756730392@qq.com", emailCodeVO.getEmail(),"验证码",mail);
        });
        log.info("执行的时间为：{}", System.currentTimeMillis() - x);
    }

    @Override
    public void bindEmail(BindEmailVO bindEmailVO) {
        int code = Integer.parseInt(redisTemplate.opsForValue().get(EmailCodeType.BIND_EMAIL.name() + bindEmailVO.getName()).toString());
        if (bindEmailVO.getCode() != code) {
            throw new ExceptionFactory(ResponseCode.AUTH_CODE_ERROR);
        }
        //删除code
        redisTemplate.delete(EmailCodeType.BIND_EMAIL.name() + bindEmailVO.getName());
        //绑定邮箱
        QueryWrapper<UserPO> wrapper = new QueryWrapper();
        wrapper.eq("name",bindEmailVO.getName());
        List<UserPO> userPOS = userMapper.selectList(wrapper);
        Optional.ofNullable(userPOS).orElseThrow(() -> new BaseException(ResponseCode.USER_NAME_ERROR));
        UserPO userPO = userPOS.get(0);
        userPO.setEmail(bindEmailVO.getEmail());
        userMapper.updateById(userPO);
    }

    @Override
    public void updateAccountPasswordByOldPwd(UpdatePasswordByOldPwdVO updatePasswordByOldPwdVO) {
        QueryWrapper<UserPO> wrapper = new QueryWrapper();
        wrapper.eq("name",updatePasswordByOldPwdVO.getName());
        List<UserPO> userPOS = userMapper.selectList(wrapper);
        Optional.ofNullable(userPOS).orElseThrow(() -> new BaseException(ResponseCode.USER_NAME_ERROR));

        String pwd = md5Utils.encryptedMd5(updatePasswordByOldPwdVO.getOldPwd());
        if (!userPOS.get(0).getPassword().equals(pwd)) {
            throw new ExceptionFactory(ResponseCode.USER_PASSWORD_ERROR);
        }

        UserPO userPO = userPOS.get(0);
        userPO.setPassword(md5Utils.encryptedMd5(updatePasswordByOldPwdVO.getNewPwd()));
        userMapper.updateById(userPO);
    }

    @Override
    public void updateAccountPasswordByCode(UpdatePasswordByCodeVO updatePasswordByCodeVO) {
        QueryWrapper<UserPO> wrapper = new QueryWrapper();
        wrapper.eq("name",updatePasswordByCodeVO.getName());
        List<UserPO> userPOS = userMapper.selectList(wrapper);
        Optional.ofNullable(userPOS).orElseThrow(() -> new BaseException(ResponseCode.USER_NAME_ERROR));

        if (updatePasswordByCodeVO.getCode() != Integer.parseInt(redisTemplate.opsForValue().get(EmailCodeType.PASSWORD.name() + updatePasswordByCodeVO.getName()).toString())) {
            throw new ExceptionFactory(ResponseCode.AUTH_CODE_ERROR);
        }

        UserPO userPO = userPOS.get(0);
        userPO.setPassword(md5Utils.encryptedMd5(updatePasswordByCodeVO.getNewPwd()));
        userMapper.updateById(userPO);
    }


}
