package com.example.stomp.socket;

import com.example.stomp.dto.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
// websocket이 어디에 매핑될것인지
public class WebSocketMapping {
    // STOMP over WebSocket
    private final SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/chat")
    public void sendChat(
            ChatMessage chatMessage,
            @Headers Map<String, Object> headers
    ){
        log.info(chatMessage.toString());
        log.info(headers.toString());
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        chatMessage.setTime(time);
        simpMessagingTemplate.convertAndSend(
                String.format("/topic/%s", chatMessage.getRoomId()),
                chatMessage
        );
    }

    // 누군가가 구독할때 실행하는 메소드
    @SubscribeMapping("/topic/{roomId}")
    public ChatMessage sendGreet(
            @DestinationVariable("roomId")
            Long roomId
    ) {
        log.info("new subscription to {}", roomId);
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setRoomId(roomId);
        chatMessage.setSender("admin");
        chatMessage.setMessage("hello!");
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        chatMessage.setTime(time);
        return chatMessage;
    }
}
