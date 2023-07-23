package com.example.auth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {
    @GetMapping
    public String root(){
        return "hello"; // 로그인 한 사용자만 볼 수 있는 페이지
    }

    // 사용자 인증 없이 접근 가능
    @GetMapping("no-auth")
    public String noAuth(){

        return "사용자 인증 없이 접근 가능한 no auth";
    }

    // 인증받은 사용자만 접근 가능
    @RequestMapping("require-auth")
    public String requireAuth(){
        return "인증받은 사용자만 접근 가능한 require-auth";
    }

}
