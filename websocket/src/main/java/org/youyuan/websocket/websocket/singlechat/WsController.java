package org.youyuan.websocket.websocket.singlechat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.Date;

/**
 * @author YJP
 */
@Controller
public class WsController {

    private static int num = 0;

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    //@MessageMapping用来接收/welcome路径发送的消息
    @MessageMapping("/app/welcome")
    public void say(RequestMessage message) {
        System.out.println(message.getName());
        //转发的路径 群发送
        simpMessagingTemplate.convertAndSend("/topic/getResponse",new ResponseMessage("welcome," + message.getName() + " !"));
    }

    /**
     * 定时推送功能
     */
//    @Scheduled(cron = "0/5 * * * * *")
    public void cronSendMessage() {
        //转发的路径 群发送
        simpMessagingTemplate.convertAndSend("/app/getResponse",new ResponseMessage("welcome," + new Date()));
    }


}
