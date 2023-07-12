package com.example.consumer;

import com.example.consumer.dto.JobPayload;
import com.example.consumer.entity.JobEntity;
import com.example.consumer.entity.JobRepository;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
// 이 클래스는 RabbitMQ 의 Queue에 적재되는 메세지를 받아서 처리하기 위한 Queue라는 뜻
// 해당 큐에 반응한다. 반응했으면 어떤 한 메소드를 실행해야 한다. 그 메소드를 지정해주는 것은? @RabbitHandler
@RabbitListener(queues = "boot.amqp.worker-queue") // 식별자를 통해 정의된 큐를 불러오는 것
@RequiredArgsConstructor
public class ConsumerService {

    private final JobRepository jobRepository;
    private final Gson gson;

    @RabbitHandler // 어떤 한 메소드를 실행해야 한다. 그 메소드를 지정.
    public void receive(String message) throws InterruptedException { // json 형태의 jobpayload 들어옴
        JobPayload newJob = gson.fromJson(message, JobPayload.class); // 역직렬화
        String jobId = newJob.getJobId();
        log.info("Received Job: {}", jobId);

        // jobId로 entity 검색
        Optional<JobEntity> optionalJob = jobRepository.findByJobId(jobId);
        // TODO 예외처리 해줘야 함. 지금은 생략!

        // 요청을 처리상태로 업데이트
        JobEntity jobEntity = optionalJob.get();
        jobEntity.setStatus("PROCESSING");
        jobEntity = jobRepository.save(jobEntity);

        log.info("Start Processing Job: {}", jobId);
        TimeUnit.SECONDS.sleep(5); // 처리하는 시간이 5초 걸린다고 치고 재우기

        // 요청을 완료 상태로 업데이트
        jobEntity.setStatus("DONE");
        jobEntity.setResultPath(String.format
                ("/media/user-uploaded/processed/%s", newJob.getFilename()));
        jobRepository.save(jobEntity);

        // 기록
        log.info("Finished Job: {}", jobId);
    }
}
