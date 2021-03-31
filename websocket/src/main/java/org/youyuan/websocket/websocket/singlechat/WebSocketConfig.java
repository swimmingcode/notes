package org.youyuan.websocket.websocket.singlechat;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.handler.invocation.HandlerMethodArgumentResolver;
import org.springframework.messaging.handler.invocation.HandlerMethodReturnValueHandler;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

import java.util.List;


/**
 * @author YJP
 */
@Slf4j
@Configuration
//注解开启使用STOMP协议来传输基于代理(message broker)的消息,这时控制器支持使用@MessageMapping,就像使用@RequestMapping一样
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * 记录当前连接的个数
     */
//    private AtomicInteger personCount  = new AtomicInteger();

    @Override
    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
//        personCount.incrementAndGet();
//        log.info("当前在线人数为{}",this.personCount);
        //定义一个前缀为"/endpointSang" 的endPoint,并开启sockjs支持
        stompEndpointRegistry.addEndpoint("/endpointSang").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //表示设置消息代理的前缀
        //表示客户端订阅地址的前缀信息，也就是客户端接收服务端消息的地址的前缀信息
        //如果消息的前缀是 /topic 就会将消息发送给代理对象（broker），再由消息代理将消息广播给当前连接的客户端
        registry.enableSimpleBroker("/topic","/queue");
        //指服务端接收地址的前缀，意思就是说客户端给服务端发消息的地址的前缀
        //表示配置一个或者多个前缀，通过这些前缀过滤出需要被注解方法处理的消息
        //例如，前缀为/app的destination可以通过@MessageMapper注解的方法处理，而其他destination(例如："/topic")将直接交给broker处理
//        registry.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void configureWebSocketTransport(WebSocketTransportRegistration registry) {
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
    }

    @Override
    public void configureClientOutboundChannel(ChannelRegistration registration) {
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
    }

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
    }

    @Override
    public boolean configureMessageConverters(List<MessageConverter> messageConverters) {
        return false;
    }
}
