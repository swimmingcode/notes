package org.youyuan.redis.mq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.awt.print.Book;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/3/10 17:07
 */
@Component
public class ProduceFactory {

    @Autowired
    RedisTemplate<String,Object> template;

    public boolean produce(String queueName,String message) {
        Long aLong = template.opsForList().leftPush(queueName, message);
        return aLong > 0 ? true : false;
    }

    public String consume(String queueName) {
        return ((String) template.opsForList().rightPop(queueName));
    }


}
