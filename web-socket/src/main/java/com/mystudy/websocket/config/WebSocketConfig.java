package com.mystudy.websocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker // 웹소켓 메세지브로커 활성화
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 메세지 브로커 설정
        // 클라이언트가 듣기 위한 경로
        // 소켓을 사용하는 모든 경로를 이것으로 시작
        registry.enableSimpleBroker("/topic");
        // 다음에 정의할 서버 측 엔드포인트에 대한 Prefix를 설정
        registry.setApplicationDestinationPrefixes("/ws");


    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // STOMP 규약을 사용하는 WebSocket 엔드포인트를 구성하는 메소드
        registry.addEndpoint("/my-websocket").withSockJS(); // 이 경로의 통신들이 STOMP 규약을 지켜서 보냄
    }
}
