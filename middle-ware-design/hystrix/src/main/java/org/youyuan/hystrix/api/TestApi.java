package org.youyuan.hystrix.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.youyuan.hystrix.anno.DoHystrix;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/5/6 13:17
 */
@Slf4j
@RestController
public class TestApi {
    /**
     * 测试：http://localhost:8081/api/queryUserInfo?userId=aaa
     */
    @DoHystrix(timeoutValue = 350, returnJson = "{\"code\":\"1111\",\"info\":\"调用方法超过350毫秒，熔断返回！\"}")
    @RequestMapping(path = "/api/queryUserInfo", method = RequestMethod.GET)
    public UserInfo queryUserInfo(@RequestParam String userId) throws InterruptedException {
        // TODO: 2021/5/6 Hystrix是如何计算出方法执行的时间
        log.info("查询用户信息，userId：{}", userId);
//        Thread.sleep(1000);
        return new UserInfo("虫虫:" + userId, 19, "天津市东丽区万科赏溪苑14-0000");
    }
}
