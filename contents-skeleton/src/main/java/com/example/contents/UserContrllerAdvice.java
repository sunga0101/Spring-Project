package com.example.contents;

import com.example.contents.dto.ResponseDto;
import com.example.contents.exceptions.Status400Exception;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
//각 Controller에 나뉘어진 ExceptionHandler 메소드를 모으는 용도
public class UserContrllerAdvice {
    @ExceptionHandler(Status400Exception.class)
    public ResponseEntity<ResponseDto> handleIllegalState(IllegalStateException exception) {
        ResponseDto response = new ResponseDto();
//        response.setMessage("UserControllerAdvice에서 처리한 예외입니다,");
        response.setMessage(exception.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(Status400Exception.class)
    public ResponseEntity<ResponseDto> handle400(
            Status400Exception exception){
        ResponseDto response = new ResponseDto();
        response.setMessage(exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body(response);
    }
}
