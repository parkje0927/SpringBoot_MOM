package com.example.alarmandlogin.websocket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class EchoHandlerForAlarm extends TextWebSocketHandler {

    //로그인 중인 개별 유저
    Map<String, WebSocketSession> users = new ConcurrentHashMap<>();
}
