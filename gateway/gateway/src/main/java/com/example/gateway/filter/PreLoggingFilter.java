package com.example.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.UUID;

@Slf4j
@Component
// 요청에 대해 공통적으로 넣고 싶은 기능을 구현하고 싶다
// 클라이언트 -> 게이트웨이 요청 도달 (여기까지 실행) -> 클라이언트
public class PreLoggingFilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(
            ServerWebExchange exchange,
            GatewayFilterChain chain
    ) {
        log.trace("Executed filter in PreLoggingFilter");
        ServerHttpRequest httpRequest = exchange.getRequest(); // 현재 전달되는 http 요청을 가져옴
        // PreLoggingFilter에서 요청을 식별할 수 있는 HTTP Header를 작성
        String requestId = UUID.randomUUID().toString();
        httpRequest.mutate() // 요청을 변형하기 시작한다.
                // 나중에 PostLoggingFilter에서 해당 Header를 바탕으로
                // 실행에 걸린 시간 측정
                .headers( // 헤더 변경
                        httpHeaders -> {
                            httpHeaders.add( // 헤더 추가
                                    "x-gateway-request-id",
                                    requestId
                            );
                            httpHeaders.add(
                                    "x-gateway-request-time",
                                    String.valueOf(Instant.now().toEpochMilli())
                            );
                        }
                )
                .build();
        log.info("start transaction: {}", requestId);
        // filterChain.doFilter() 대신
        return chain.filter(exchange); // 사용자에게 응답 돌려주기 위해서
    }
}