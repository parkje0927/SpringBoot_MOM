package com.example.alarmandlogin.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * /ws-stomp 소켓을 연결하고, /sub/ 를 구독하고 있으면 메세지를 전송할 수 있다.
     * 토큰을 인증하기 위한 stompHandler 를 추가해서 연결이 되기 전에 해당 핸들러의 메소드를 실행한다.(아직 JwtTokenProvider 구현 안 함.)
     *
     * /app 으로 시작하는 대상이 있는 클라이언트에서 보낸 모든 메세지는 @MessageMapping 어노테이션이 달린 메소드로 라우팅이 된다.
     * "/app/chat.sendMessage" 인 메세지는 -> sendMessage() 로 라우팅
     * "/app/chat.addUser" 인 메세지는 -> addUser() 로 라우팅
     */

//    private final StompHandler stompHandler;
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").withSockJS();
    }

//    @Override
//    public void configureClientInboundChannel(ChannelRegistration registration) {
//        registration.interceptors(stompHandler); //핸들러 등록
//    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic"); //일종의 topic, 해당 문자열로 시작하는 message 주소값을 받아서 처리하는 Broker 를 활성화한다.
        registry.setApplicationDestinationPrefixes("/app"); //클라이언트가 서버로 메시지를 보낼 때 붙여야 하는 url prefix
    }
}
