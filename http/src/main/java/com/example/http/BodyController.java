package com.example.http;

import com.example.http.dto.ArticleDto;
import com.example.http.dto.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class BodyController {

    // `/body` 로 요청이 들어왔을 때
    // ResponseDto 데이터를 표현한 JSON 응답을 반환하는 메소드
    @PostMapping("/body")
    public @ResponseBody ResponseDto body(
            @RequestBody ArticleDto requestDto
            ){
        log.info(requestDto.toString());
        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatus(200);
        responseDto.setMessage("Success");
        return responseDto;
        // html이 아니라 사용자가 해석할 수 있는 형태의 데이터로 보내주는 것
        // -> ResponseBody
    }
}
