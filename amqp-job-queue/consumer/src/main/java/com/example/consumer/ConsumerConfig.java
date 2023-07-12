package com.example.consumer;


import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/* 어떤 큐를 사용할 것인지 정의 */
@Configuration
public class ConsumerConfig {
    @Bean
    public Queue queue() {
        return new Queue(
                // 이름: producer와 consumer가 같은 Queue를 사용하기 위해서 작성하는 식별자임
                // 같은 우체통을 바라보기 위한 우체통의 이름
                "boot.amqp.worker-queue",
                // durable : 서버(producer)가 종료된 후에도 Queue가 유지될지 옵션
                true,
                // exclusive : 현재 이 서버만 Queue를 사용할수 있는지 옵션
                // 메세지를 주고받을 입장에서는 true이면 안됨
                false,
                // autoDelete: 사용되고 있지 않은 큐를 자동으로 삭제할 것인지 옵션
                true
        );

    }
}
