package org.youyuan.websocket.websocket.singlechat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.youyuan.websocket.websocket.singlechat.RequestMessage;
import org.youyuan.websocket.websocket.singlechat.ResponseMessage;

//package org.youyuan.websocket.websocket.singlechat;
//
//
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.stereotype.Controller;
//
///**
// * Created by sang on 16-12-22.
// */
@Controller
public class WsController {

    @MessageMapping("/welcome")
    @SendTo("/topic/getResponse")
    public ResponseMessage say(RequestMessage message) {
        System.out.println(message.getName());
        return new ResponseMessage("welcome," + message.getName() + " !");
    }

    @GetMapping("/chat")
    public ModelAndView chat() {
        return new ModelAndView("ws");
    }
}
