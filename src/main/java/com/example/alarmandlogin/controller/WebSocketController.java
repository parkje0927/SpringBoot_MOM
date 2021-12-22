package com.example.alarmandlogin.controller;

import com.example.alarmandlogin.domain.Alarm;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class WebSocketController {

    /**
     * @Payload : WebSocket 에서 전송하는 데이터를 받기 위한 어노테이션
     */
    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public Alarm register(@Payload Alarm alarm, SimpMessageHeaderAccessor headerAccessor) {
        //누가 보냈는지 정보 담기
        headerAccessor.getSessionAttributes().put("username", alarm.getSender());
        return alarm;
    }

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public Alarm sendMessage(@Payload Alarm alarm) {
        return alarm;
    }
}
