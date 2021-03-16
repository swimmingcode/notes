package org.youyuan.redis.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.youyuan.redis.bean.Message;
import org.youyuan.redis.mq.ProduceFactory;



/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/3/10 14:15
 */
@RestController
@Slf4j
public class HelloController {

    @Autowired
    ProduceFactory produceFactory;

    @GetMapping("/test")
    public void test() {
        Message message = new Message();
        message.setId(1);
        message.setMessage("zs");
        produceFactory.produce("queue", JSONObject.toJSONString(message));
    }


}
