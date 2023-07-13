package com.example.chat.config;

import com.example.chat.SimpleChatHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketConfigurer {
    // TODO Bean 객체로 SimpleChatHandler 가져오기
    // 다른 객체를 빈으로 등록된 이 객체로 가져오기 (생성자 만들어서 주입)
    private final SimpleChatHandler simpleChatHandler;

    @Override
    // WebSocketHandler 객체를 등록하기 위한 메소
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(simpleChatHandler,"ws/chat") // chat.html의 ws/chat 과 동일한 path
                .setAllowedOrigins("*");

    }
}
