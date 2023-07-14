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
// 클라이언트 -> 게이트웨이 요청 전달 완료 (여기부터) -> 클라이언트에게 응답
public class PostLoggingFilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return chain.filter(exchange)
                .then( // 요청 전달 후 그리고 나서
                        Mono.fromRunnable(() -> {
                            ServerHttpRequest httpRequest = exchange.getRequest();
                            String requestId =
                                   // 앞선 필터에서는 UUID.randomUUID().toString(); 였지만
                            httpRequest.getHeaders()
                                    .getFirst("x-gateway-request-id");
                            String requestTimeString = httpRequest.getHeaders()
                                    .getFirst("x-gateway-request-time");
                            long timeEnd = Instant.now().toEpochMilli();
                            long timeStart = requestTimeString == null?
                                    timeEnd :
                                    Long.parseLong(requestTimeString);
                            log.info("Execution Time id: {}, timediff(ms): {}",
                                    requestId, timeEnd-timeStart);
                        })

                );
        // postFilter와 preFilter를 한 클래스에서 나누지 않고 구현하기도 함
    }
}