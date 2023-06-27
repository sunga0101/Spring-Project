package com.example.aop.controller;

import com.example.aop.dto.ResponseDto;
import org.springframework.web.bind.annotation.*;

@RestController
public class AopController {
    @PostMapping("/users")
    public ResponseDto addUser(@RequestBody ResponseDto dto){
        return dto;
    }
}
