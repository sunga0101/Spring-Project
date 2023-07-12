package com.example.auth.service;

import com.example.auth.jwt.JwtTokenDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("token")  // http://localhost:8080/token/** 부터 시작
public class TokenController {
    private final UserDetailsManager manager;
    private final PasswordEncoder passswordEncoder;

    public TokenController(UserDetailsManager manager, PasswordEncoder passswordEncoder) {
        this.manager = manager;
        this.passswordEncoder = passswordEncoder;
    }

    // /token/issue: JWT 발급용 Endpoint
    @PostMapping("/issue")
    public JwtTokenDto issueJ
}
