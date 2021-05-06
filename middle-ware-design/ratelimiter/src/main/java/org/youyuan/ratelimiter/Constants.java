package org.youyuan.ratelimiter;

import com.google.common.util.concurrent.RateLimiter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/5/6 15:02
 */
public class Constants {
    public static Map<String/*方法名**/, RateLimiter/*限流桶*/> rateLimiterMap = Collections.synchronizedMap(new HashMap<String, RateLimiter>());
}
