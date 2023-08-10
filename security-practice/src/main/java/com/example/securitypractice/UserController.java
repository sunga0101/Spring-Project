package com.example.securitypractice;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/login")
    public String loginForm(){
        return "login-form";
    }

    @GetMapping("/my-prifle")
    public String myProfile(){
        return "my-profile";
    }

    @GetMapping("/reqister")
    public String registerForm(){
        return "reigter-form";
    }

    @PostMapping("register")
    public String register(
        @RequestParam("username") String username,
        @RequestParam("password") String password,
        @RequestParam("password-check") String passwordCheck)
    {
        if (password.equals(passwordCheck)){
            // TODO 가입로직
            return "redirect:/users/login";
        }
        return "redirect:/users/login?fail";
    }

    }




