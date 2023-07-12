package com.example.producer.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobRepository extends JpaRepository<JobEntity, Long> {
    Optional<JobEntity> findByJobId(String jobId); // Id가 아닌 jobId로 찾을 거기 때문에
}
