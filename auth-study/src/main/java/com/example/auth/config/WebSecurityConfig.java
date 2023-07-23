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
            HttpSecurity http
    )
        throws Exception { // httpSecurity : 빌더와 같은 형태로 인증/인가 에 대한 설정을 함
        http
                // CSRF: Cross Site Request Forgery
                .csrf(AbstractHttpConfigurer::disable)
                // 1. requestMatchers를 통해 설정할 URL 지정
                // 2. permitAll(), authenticated() 등을 통해 어떤 사용자가
                //    접근 가능한지 설정
                .authorizeHttpRequests(
                        auth -> auth
                    .requestMatchers("/no-auth").permitAll() // 해당 URL 모든 요청 인증 필요 무 (바로 접근 가능)
                    .requestMatchers("users/my-profile").authenticated() // 권한 필요
                )
                // form 을 이용한 로그인 관련 설정
                .formLogin(
                        formLogin -> formLogin
                                // 로그인 하는 페이지(경로)를 지정
                                .loginPage("/users/login")
                                // 로그인 성공시 이동하는 페이지(경로)
                                .defaultSuccessUrl("/users/my-profile")
                                // 로그인 실패시 이동하는 페이지(경로)
                                .failureUrl("/users/login?fail")
                                // 로그인 과정에서 필요한 경로들을
                                // 모든 사용자가 사용할 수 있게끔 권한설정
                                .permitAll()
                )
                .logout(
                        logout -> logout
                                // 로그아웃 요청을 보낼 URL
                                .logoutUrl("users/logout") // 클라이언트 UI가 /users/logout으로 POST 요청 보내도록 함
                                // 로그아웃 성공시 이동할 URL 설정
                                .logoutSuccessUrl("users/login")
                )

        ;

        return http.build();
    }


    @Bean
    // 사용자 관리를 위한 인터페이스 구현체 Bean
    public UserDetailsManager userDetailsManager(
            PasswordEncoder passwordEncoder){
        // 임시 유저
        // 인터페이스 UserDetails, 그 구현체 클래스 User
        UserDetails user1 = User.withUsername("user1")
                .password(passwordEncoder.encode("password"))
                .build();
        UserDetails user2 = User.withUsername("user2")
                .password(passwordEncoder.encode("password"))
                .build();
        // Spring 에서 미리 만들어놓은 사용자 인증 서비스
        return new InMemoryUserDetailsManager(user1,user2);
    }

    @Bean
    // 비밀번호 암호화를 위한 Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
