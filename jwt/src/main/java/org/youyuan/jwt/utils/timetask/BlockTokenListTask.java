package org.youyuan.jwt.utils.timetask;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.youyuan.jwt.utils.common.DateUtils;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/2/16 15:27
 */
@Slf4j
@Component
public class BlockTokenListTask {

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    /**
     * 每天删除前日的缓存
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void clearTokenCache() {
        log.info("开始删除token黑名单缓存");
        String date = DateUtils.getBeforeDayFormatDay(2);
        redisTemplate.delete(date);
    }

}
