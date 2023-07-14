package com.example.gateway.config;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@RequiredArgsConstructor
// 1. 어떤 URL로 들어온 요청을, 2. 다시 어디로 보낼까
public class RoutingConfig {
    @Bean
    // RouteLocatorBuilder를 통해서 만든 (설정하고자 하는 대로 설정한) URL 설정을
    // RouteLocator로 URL에 설정 적용
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder){
        //  /article 로 들어온 요청을 전부 http://localhost:8080 으로 보내보자
        return builder.routes()
        .route( // gateway 설정 구성을 시작하겠다.
                "articles", // 어떤 경로인지 구별할 ID
                predicate -> predicate // 어떻게 동작할지 정의한다
                        .path("/articles/**") // 요청경로 조건 (나에게 /article 로 오는 요청에 대해서)
                        .uri("http://localhost:8082") // 요청을 이 곳(다른 서버 경로)로 보낸다
                // 요청: http://localhost:8080/article/이하-모든-경로 로 들어오면
                // 요청 전달 : http://localhost:8082/article/이하-모든-경로 로 전달한다
        ).build();
    }

}

