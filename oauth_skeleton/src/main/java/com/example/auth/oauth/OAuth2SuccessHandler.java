package com.example.auth.oauth;


import com.example.auth.entity.CustomUserDetails;
import com.example.auth.jwt.JwtTokenUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
// OAuth2 통신이 성공적으로 끝났을 때 사용
// JWT 를 통한 인증 구현을 하고싶기 때문에
// ID Provider에게 받은 정보로 그 유저의 JWT를 발급하는 역할을 한다
// JWT를 발급하고 클라이언트(브라우저)가 저장할 수 있도록 특정 URL로 리다이렉트 시키자 (HTTP는 상태를 저장할 수 없으니)
public class OAuth2SuccessHandler extends
        // 인증 성공 후에 특정 URL로 리다이렉트 시키고 싶을 때 활용할 수 있는 successHandler
        SimpleUrlAuthenticationSuccessHandler {
    private final JwtTokenUtils tokenUtils;
    private final UserDetailsManager userDetailsManager;

    public OAuth2SuccessHandler(JwtTokenUtils tokenUtils, UserDetailsManager userDetailsManager) {
        this.tokenUtils = tokenUtils;
        this.userDetailsManager = userDetailsManager;
    }

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        // OAuth2UserServiceImpl 에서 반환한 DefaultOAuth2User 가 저장된다
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        String username = oAuth2User.getAttribute("email");
        String provider = oAuth2User.getAttribute("provider");
        String providerId = oAuth2User.getAttribute("id").toString();

        // jwt 생성
//        String jwt = tokenUtils.generateToken(User
//                .withUsername(oAuth2User.getName())
//                .password(oAuth2User.getAttribute("id"))
//                .build());
        String jwt = tokenUtils.generateToken(CustomUserDetails.builder()
                .username(oAuth2User.getName())
                .password(providerId)
                .email(username)
                .provider(provider)
                .providerId(providerId)
                .build());

        // 목적지 URL 설정
        // 우리 서비스의 Frontend 구성에 따라 유연하게 대처
        String targetUrl = String.format("http://localhost:808/token/val?token=%s",jwt);
        // 실제 Redirect 응답 생성
        getRedirectStrategy().sendRedirect(request,response,targetUrl);


    }

}
