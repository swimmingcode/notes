package org.youyuan.mailserver;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@EnableCaching
public class MailServer {

    /**
     * 消息队列
     * */
    @Bean
    Queue queue(){
        return new Queue("youyuan");
    }

    public static void main(String[] args) {
        SpringApplication.run(MailServer.class, args);
    }
}
