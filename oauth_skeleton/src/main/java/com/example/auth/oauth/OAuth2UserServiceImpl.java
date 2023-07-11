package com.example.auth.oauth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class OAuth2UserServiceImpl extends DefaultOAuth2UserService {

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User =  super.loadUser(userRequest);// 상속받은 클래스에서 user 전달받음
        // Todo 사용할 데이터 재정의
        Map<String ,Object> attributes = new HashMap<>(); // 사용할 데이터를 다시 정리하는 목적의 Map
        attributes.put("provider","naver");

        // 받은 사용자 데이터를 정의한다
        Map<String ,Object> responseMap = oAuth2User.getAttribute("response"); // response에 대한 것들만
        attributes.put("id",responseMap.get("id"));
        attributes.put("email",responseMap.get("email"));
        attributes.put("nickname",responseMap.get("nickname"));
        String nameAttribute = "email";

        // 기본설정으로는 여기까지 오면 인증 성공
        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority("USER")),
                attributes, nameAttribute
        );
    }
}
