package com.example.http;

import com.example.http.dto.ArticleDto;
import com.example.http.dto.ArticleWithCommentsDto;
import com.example.http.dto.ResponseDto;
import com.example.http.dto.WriterDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        log.info("POST /body "+requestDto.toString());
        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatus(200);
        responseDto.setMessage("Success");
        return responseDto;
        // html이 아니라 사용자가 해석할 수 있는 형태의 데이터로 보내주는 것
        // -> ResponseBody
    }

    @PostMapping("/body-2")
    @ResponseBody
    public ResponseDto body2(@RequestBody WriterDto dto)
    {
        log.info("POST /body "+dto.toString());
        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatus(200);
        responseDto.setMessage("Success");
        return responseDto;
    }

    @PostMapping("/body-3")
    @ResponseBody
    public ResponseDto body3(@RequestBody ArticleWithCommentsDto dto){
        log.info("POST /body "+dto.toString());
        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatus(200);
        responseDto.setMessage("Success");
        System.out.println(dto.getComment());
        return responseDto;
    }

    @PostMapping("/body-4")
    @ResponseBody
    public ResponseDto body4(@RequestBody ArticleWithCommentsDto dto){
        log.info("POST /body "+dto.toString());
        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatus(200);
        responseDto.setMessage("Success");
        System.out.println(dto.getComment());
        return responseDto;
    }

    @PostMapping("/entity")
    public ResponseEntity<ResponseDto> entuty( @RequestBody ArticleDto dto)
    {
        log.info("POST /entity " + dto.toString());
        ResponseDto response = new ResponseDto();
        response.setMessage("success");
        response.setStatus(200);

        // ResponseEntity 객체 그냥 쓰기
        ResponseEntity<ResponseDto> responseEntity
                = new ResponseEntity<>(
                response, HttpStatus.CREATED);
//        return responseEntity;

        // 커스텀 헤더 만들고 함께 응답하기
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-likelion-custom", "Hello World!");
        return new ResponseEntity<>(response, headers, HttpStatus.ACCEPTED);
    }
}
