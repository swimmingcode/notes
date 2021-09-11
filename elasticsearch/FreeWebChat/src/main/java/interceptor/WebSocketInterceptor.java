package com.xnpe.fchat.interceptor;

import com.xnpe.fchat.data.MessageKey;
import org.json.JSONObject;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 此拦截器用来在客户端向服务端发起初次连接时，记录客户端拦截信息
 *
 * @author YJP
 */
public class WebSocketInterceptor implements HandshakeInterceptor {

    /**
     * 进行用户信息保存，这里我们将用户名和房间号保存到Session
     *
     * @param serverHttpRequest
     * @param serverHttpResponse
     * @param webSocketHandler
     * @param map
     * @return
     * @throws Exception
     */
    @Override
    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {
        if (serverHttpRequest instanceof ServletServerHttpRequest) {
            String INFO = serverHttpRequest.getURI().getPath().split("INFO=")[1];
            if (INFO != null && INFO.length() > 0) {
                JSONObject jsonObject = new JSONObject(INFO);
                String command = jsonObject.getString("command");
                if (command != null && MessageKey.ENTER_COMMAND.equals(command)) {
                    System.out.println("当前session的ID="+ jsonObject.getString("name"));
                    ServletServerHttpRequest request = (ServletServerHttpRequest) serverHttpRequest;
//                    HttpSession session = request.getServletRequest().getSession();
                    map.put(MessageKey.KEY_WEBSOCKET_USERNAME, jsonObject.getString("name"));
                    map.put(MessageKey.KEY_ROOM_ID, jsonObject.getString("roomId"));
                }
            }
        }
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {
        System.out.println("进来webSocket的afterHandshake拦截器！");
    }
}
