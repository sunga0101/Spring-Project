package com.example.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

// 5.7 버전 이전: extends WebSecurityConfigurerAdapter
// 6.1 버전 이후: Builder -> Lambda 를 이용 DSL 기반 설정
@Configuration
// @EnableWebSecurity  // 2.1 버전 이후 Spring Boot Starter Security 에서는 필수 아님
public class WebSecurityConfig {
    @Bean // 반한하는 것을 빈으로 생성한다
    // 런타임에 해당 빈에 대한 빈 정의 및 서비스 요청을 생성할 수 있음
    public SecurityFilterChain securityFilterChain( // 해당 요청에 적용되는지 여부를 결정하기 위해
                                                    // HttpServletRequest와 일치시킬 수 있는 필터 체인을 정의
            HttpSecurity http) throws Exception { // httpSecurity : 빌더와 같은 형태로 인증/인가 에 대한 설정을 함
        http.authorizeHttpRequests( // HTTP 요청의 인증 필요 여부를 설정
            auth -> auth.requestMatchers("/no-auth").permitAll() // 해당 URL 모든 요청 인증 필요 무 (바로 접근 가능)
        );
        return http.build();
    }
}
