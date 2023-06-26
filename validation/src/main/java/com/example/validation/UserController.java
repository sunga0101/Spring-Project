package com.example.validation;

import com.example.validation.dto.UserDto;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
@Slf4j
@RestController
public class UserController {
    @PostMapping("/users")
    // ResponseDto 대신 Map을 적절히 사용한다
    public ResponseEntity<Map<String,String>> addUser(
            // @Valid : UserDto가 우리가 정의한 요구사항을
            // 지키고 있는지 유효성 검사
           @Valid @RequestBody UserDto dto
    ){
        log.info(dto.toString());
        Map<String,String> responseBody = new HashMap<>();
        responseBody.put("message","success!");

        return ResponseEntity.ok(responseBody);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 예외의 상태 코드를 Bad Request로 고정
    public Map<String ,String> handleValidationException(
            MethodArgumentNotValidException exception
    ){
        Map<String ,String> errors = new HashMap<>();
        for (FieldError error : exception.getBindingResult().getFieldErrors()){
            errors.put(error.getField(), error.getDefaultMessage());
        }

        return errors;
    }



}
