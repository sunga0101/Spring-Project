package com.example.producer;

import com.example.producer.dto.JobPayload;
import com.example.producer.dto.JobTicket;
import com.example.producer.jpa.JobEntity;
import com.example.producer.jpa.JobRepository;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProducerService {
    // RabbitMQ에 메세지를 적재하기 위한 클래스
    private final RabbitTemplate rabbitTemplate;
    // config에서 설정 정의한 처리할 작업정보가 대기하는 Queue
    private final Queue jobQueue;
    private final JobRepository jobRepository;
    // 객체를 쉽게 JSON 문자열로 직렬화 해주는 라이브러리
    private final Gson gson;

    // filename을 인자로 바고
    // filename을 바탕으로 JSON으로 직렬화된 작업 정보를
    // Queue에 적재한 뒤에
    // 사용자에게 JobTicket을 반환하는 메소드
    public JobTicket send(String filename){
        // jobId 발행
        String jobId = UUID.randomUUID().toString();
        JobTicket jobTicket = new JobTicket(jobId); // JobTicket @AllArgs해준 이유

        // JobPayload 생성 (Consumer에게 보낼) (Consumer가 확인하는 데이터)
        JobPayload payload = new JobPayload(
                jobId,
                filename,
                String.format("/media/user-uploaded/raw/%s",filename)
        );

        // JobEntity로 작업 내역 입력 기록
        JobEntity jobEntity = new JobEntity();
        jobEntity.setJobId(jobId);
        jobEntity.setStatus("IDLE");
        jobRepository.save(jobEntity);



        // Message Broker에게 메세지 전달
        rabbitTemplate.convertAndSend(
                // 큐의 이름이 들어감 (어떤 큐에 적재할지에 대한 이름)
                jobQueue.getName(),
                // 전달하고 싶은 문자열이 들어감
                gson.toJson(payload)
        );

        log.info("Sent Job: {}", jobTicket.getJobId());
        return jobTicket;



    }





}
