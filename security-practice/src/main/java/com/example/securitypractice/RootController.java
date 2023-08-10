package com.example.securitypractice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {
    @GetMapping("/no-auth")
    public String noAuth(){
        return "no auth!";
    }

    @GetMapping("/require-auth")
    public String reAuth(){
        return "require auth!";
    }
}
