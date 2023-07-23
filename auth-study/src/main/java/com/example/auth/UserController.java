package com.example.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller  // 로그인 페이지를 보여줄려고
@RequestMapping("/users")
public class UserController {
    private final UserDetailsManager userDetailsManager;
    private final PasswordEncoder passwordEncoder;

    public UserController(
            UserDetailsManager userDetailsManager,
            PasswordEncoder passwordEncoder){
        this.userDetailsManager = userDetailsManager;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping("/login")
    public String loginForm() {
        return "login-form"; // 로그인폼태
    }

    @GetMapping("/my-profile")
    public String myProfile(Authentication authentication) {
        log.info(authentication.getName());
        return "my-profile"; // 마이페이지
    }

    @GetMapping("/register")
    public String signUpForm(){
        return "register-form";
    }

    @PostMapping("/register")
    public String signUpRequest(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("password-check") String passwordCheck
    ){
        if (passwordCheck.equals(password))
            {
                // 회원가입 진행
                userDetailsManager.createUser
                        (User.withUsername(username)
                                .password(passwordEncoder
                                .encode(password))
                                .build());

                // 회원가입 성공 후 로그인 페이지로
                return "redirect:/users/login";
            }
        log.warn("password does not match...");
        return "redirect:/users/register?error";
    }

}
