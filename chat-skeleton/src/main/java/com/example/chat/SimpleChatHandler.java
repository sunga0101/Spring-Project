package com.example.chat;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class SimpleChatHandler extends TextWebSocketHandler {
    // 현재 연결되어있는 클라이언트를 관리하기 위한 리스트
    private final List<WebSocketSession> sessions = new ArrayList<>();
    // 사람 이름으로 구별하고 싶다면?
    private Map<String ,WebSocketSession> sessionByUsername;


    public void broadcast(String message)  throws IOException{
        for (WebSocketSession connected: sessions)
            connected.sendMessage(new TextMessage(message));
    }

    @Override
    // WebSocket 최초 연결시 실행
    public void afterConnectionEstablished( // 현재 연결된 클라이언트를 나타내는 객체
            WebSocketSession session // 어떤 사용자인지 판단하는 기준
    ) throws Exception {
        // 방금 참여한 사용자를 저장
        sessions.add(session);
        log.info("connected with session id: {}, total sessions: {}",session.getId(),sessions.size());
    }

    @Override
    // WebSocket 메세지를 받으면 실행
    protected void handleTextMessage(
            WebSocketSession session,
            TextMessage message // 사용자가 보내는 텍스트 메세지
    ) throws Exception {
        String payload = message.getPayload(); // 메세지에서 텍스트를 뽑아온다
        log.info("received: {}", payload);
        for (WebSocketSession connected: sessions)
            connected.sendMessage(message);
    }

    @Override
    // WebSocket 연결이 종료되었을 때
    public void afterConnectionClosed(
            WebSocketSession session,
            CloseStatus status
    ) throws Exception {
        log.info("connection with {} closed", session.getId());
        // 세션이 끊기게 되면 해당 세션의 메소드를 호출할 때
        // 에러가 발생해서 서버가 멈출수있으니,
        // 더 이상 세션 객체를 보유하지 않도록
        sessions.remove(session);
    }
}
