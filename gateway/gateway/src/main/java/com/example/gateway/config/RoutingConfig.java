package com.example.gateway.config;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
        // 1. route article
        .route( // gateway 설정 구성을 시작하겠다.
                "articles", // 어떤 경로인지 구별할 ID
                predicate -> predicate // 어떻게 동작할지 정의한다
                        .path("/articles/**") // 요청경로 조건 (나에게 /article 로 오는 요청에 대해서)
                        .uri("http://localhost:8082") // 요청을 이 곳(다른 서버 경로)로 보낸다
                // 요청: http://localhost:8080/article/이하-모든-경로 로 들어오면
                // 요청 전달 : http://localhost:8082/article/이하-모든-경로 로 전달한다
        )
        // 2. route auth
        // 각기 다른 root url을 가지고 있는 auth 서버에게 요청을 보내고 싶다
                // ex. 8081/token, 8081/issue 등
        .route(
                "auth",
                predicate -> predicate
                        .path("/auth/**")
                        .filters(filter -> filter
                                // auth/ 부분을 필터링하자
                                .rewritePath("/auth/(?<path>.*)",// 이 경로로 요청이 들어왔을 때
                                        "/${path}" // path 부분을 담아서 경로를 /path로 변경해줌. (auth 떼고)
                                ))
                        .uri("http://localhost:8081")
                // 요청: http://localhost:8080/auth/이하-모든-경로 로 들어오면
                // 요청 전달 : http://localhost:8081/이하-경로 로 전달한다

        )
        .build();
    }

}

