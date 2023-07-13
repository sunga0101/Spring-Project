package com.mystudy.websocket.controller;

import com.mystudy.websocket.dto.MessageDto;
import com.mystudy.websocket.dto.ResponseDto;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class MessageController {

    //이때 전달하는 /message 인자는 이전 Configuration 구성 중 작성한
    // /ws 뒤에 붙여 명확한 엔드포인트를 가르키는 용도로 활용 즉. '/ws/message'로 요청 보냄
    @MessageMapping("/message") // STOMP 엔드포인트를 구성할때는 @RequestMapping 대신 @MessageMapping 을 사용
    @SendTo("/topic/messages") // 해당 경로의 모든 클라이언트에게 보냄
    public ResponseDto getMessage(MessageDto dto) throws InterruptedException {
        Thread.sleep(1000);
        return new ResponseDto(HtmlUtils.htmlEscape(dto.getMessageContent()));
    }



}
