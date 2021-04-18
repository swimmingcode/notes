package com.xnpe.fchat.config;

import interceptor.MyHandler;
import interceptor.WebSocketInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @author YJP
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        //注册WebSocket地址，并附带了{INFO}参数，用来注册的时候携带用户信息
        registry.addHandler(new MyHandler(),"/webSocket/{INFO}")
                .setAllowedOrigins("*")
                .addInterceptors(new WebSocketInterceptor());
    }
}
