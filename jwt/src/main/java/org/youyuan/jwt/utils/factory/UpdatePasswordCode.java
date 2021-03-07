package org.youyuan.jwt.utils.factory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.youyuan.jwt.utils.common.CodeUtils;
import org.youyuan.jwt.utils.common.EmailUtils;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/2/17 16:05
 */
@Slf4j
@Component
public class UpdatePasswordCode implements EmailCode {

    private final String UPDATE_PASSWORD = "updatePassword";

    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    TemplateEngine templateEngine;
    @Resource(name = "taskExecutor")
    ThreadPoolTaskExecutor threadPoolTaskExecutor;
    @Autowired
    EmailUtils emailUtils;

    @Override
    public int getEmailCode(String name,String email) {
        long x = System.currentTimeMillis();
        //生成随机验证码
        Integer code = CodeUtils.randomEmailCode();
        //存入Redis,设置过期时间为5分钟
        redisTemplate.opsForValue().set(UPDATE_PASSWORD + name + email,code,5L, TimeUnit.MINUTES);
        //发送邮箱
        Context context = new Context();
        context.setVariable("code",code);
        String mail = templateEngine.process("email", context);
        //异步处理
        threadPoolTaskExecutor.execute(()->{
            emailUtils.sendHtmlMail("1756730392@qq.com",email,"验证码",mail);
        });
        log.info("time={}", System.currentTimeMillis() - x);
        return 0;
    }

}
