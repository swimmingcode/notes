package org.youyuan.redis.mq;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.youyuan.redis.bean.Message;

import java.util.concurrent.TimeUnit;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/3/10 17:23
 */
@Slf4j
@Component
public class MqListener implements Runnable{

    @Autowired
    ProduceFactory produceFactory;

//    @PostConstruct
//    public void listen() {
//        new Thread(()->{
//            while (true) {
//                String queue = produceFactory.consume("queue");
//                if (StringUtils.isNoneBlank(queue)){
//                    Message message = JSONObject.parseObject(queue, Message.class);
//                    log.info("{}", message);
//                }
//                try {
//                    TimeUnit.SECONDS.sleep(3);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//    }

    @Override
    public void run() {
        while (true) {
            String queue = produceFactory.consume("queue");
            if (StringUtils.isNoneBlank(queue)){
                Message message = JSONObject.parseObject(queue, Message.class);
                log.info("{}", message);
            }
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
