package org.youyuan.jwt.utils.common.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RedisUtils {

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    /**
     * String字符串操作
     * 设置key
     *
     * @param key
     * @param value
     */
    public void opsForValue(String key,Object value) {
        redisTemplate.opsForValue().set(key,value);
    }

    /**
     * String字符串操作
     * 设置key、过期时间
     *
     * @param key
     * @param value
     * @param time
     */
    public void opsForValueWithExpired(String key,Object value,long time) {
        redisTemplate.opsForValue().set(key,value,time);
    }

    /**
     * String字符串操作
     * 获取value
     *
     * @param key
     * @return
     */
    public Object opsForValueGetValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * List集合操作
     * 设置key 头插法
     *
     * @param key
     * @param value
     */
    public void opsForList(String key, Object value) {
        redisTemplate.opsForList().leftPush(key,value);
    }

    /**
     * List集合操作
     * 获取所有value
     *
     * @param key
     * @return
     */
    public List<Object> opsForListGetValue(String key) {
        List<Object> range = redisTemplate.opsForList().range(key, 0, -1);
        return range;
    }
}
