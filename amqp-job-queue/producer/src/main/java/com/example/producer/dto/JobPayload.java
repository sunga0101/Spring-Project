package com.example.producer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/* 프로듀서 <-> 래핏 */
@Data
@AllArgsConstructor
// Producer가 발생시킬 Job을 정의한 DTO
public class JobPayload {
    private String jobId;
    private String filename;
    private String path;
}