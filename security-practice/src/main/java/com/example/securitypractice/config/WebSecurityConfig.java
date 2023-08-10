package com.example.securitypractice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {
    @Bean // 빈으로 만들어줘야지
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http // 이제 설정을 하면 된다
                .csrf(AbstractHttpConfigurer:: disable)   //   .csrf().disable() 예전에는 이렇게 했음
                .authorizeHttpRequests( // 난 인증을 하고 싶어
                    authHttp -> authHttp // 람다식 형태로
                            .requestMatchers("/no-auth") // 인증 없는 경로로 들어온다면
                            .permitAll() // 모두 허용해줌
                            .requestMatchers("/users/register")
                            .anonymous() // 익명, 즉 모두 들어가도록
                            .anyRequest() // 나머지 경로들은
                            .authenticated() // 인증 되도록 한다
                )
                .formLogin(
                        formLogin -> formLogin
                                .loginPage("/users/login") // 로그인 페이지를 이 경로로 하겠다
                                .defaultSuccessUrl("/users/my-profile") // 성공하면 여기로
                                .failureUrl("/users/login>fail") // 실패시 여기로
                                .permitAll()
                )
                .logout(
                        logout -> logout
                                .logoutUrl("users/logout")
                                .logoutSuccessUrl("/users/login")
                )
                ;
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() { // 단방향 암호화
       // -> 디코딩이 힘들다
        return new BCryptPasswordEncoder();

    }
}
