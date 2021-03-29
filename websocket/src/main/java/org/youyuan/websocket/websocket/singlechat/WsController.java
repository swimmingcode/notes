package org.youyuan.websocket.websocket.singlechat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.util.Date;

/**
 * @author YJP
 */
@Controller
public class WsController {

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    //@MessageMapping用来接收/welcome路径发送的消息
    @MessageMapping("/welcome")
    public void say(RequestMessage message) {
        System.out.println(message.getName());
        //转发的路径 群发送
        simpMessagingTemplate.convertAndSend("/app/getResponse",new ResponseMessage("welcome," + message.getName() + " !"));
    }

    @Scheduled(cron = "0/5 * * * * *")
    public void cronSendMessage() {
        //转发的路径 群发送
        simpMessagingTemplate.convertAndSend("/app/getResponse",new ResponseMessage("welcome," + new Date() + " !"));
    }


}
