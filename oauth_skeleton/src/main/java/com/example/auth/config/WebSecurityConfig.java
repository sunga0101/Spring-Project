package com.example.auth.config;

import com.example.auth.jwt.JwtTokenFilter;
import com.example.auth.oauth.OAuth2SuccessHandler;
import com.example.auth.oauth.OAuth2UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

@Configuration
public class WebSecurityConfig {
    private final JwtTokenFilter jwtTokenFilter;

    // 등록 과정
    // bean 객체로 받아오기
    private final OAuth2UserServiceImpl oAuth2UserService;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;
    public WebSecurityConfig(JwtTokenFilter jwtTokenFilter,
                             OAuth2UserServiceImpl oAuth2UserService,
                             OAuth2SuccessHandler oAuth2SuccessHandler) {
        this.jwtTokenFilter = jwtTokenFilter;
        this.oAuth2UserService = oAuth2UserService;
        this.oAuth2SuccessHandler = oAuth2SuccessHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    )
        throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authHttp -> authHttp
                        .requestMatchers("/token/**", "/views/**")
                        .permitAll()
                )
                .oauth2Login(oauth2Login -> oauth2Login
                        .loginPage("/views/login")
                        .successHandler(oAuth2SuccessHandler)
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(oAuth2UserService))
                )
                .sessionManagement(
                        sessionManagement -> sessionManagement
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtTokenFilter, AuthorizationFilter.class);


        return http.build();
    }
    @Bean
    // 비밀번호 암호화를 위한 Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
