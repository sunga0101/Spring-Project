package com.example.producer.dto;

import com.example.producer.jpa.JobEntity;
import lombok.Data;
/* 프로듀서 <-> 사용자 */

@Data
// 사용자에게 요청 처리 상태를 알리기 위한 DTO
public class JobStatus {
    private String jobId;
    private String status;
    private String resultPath;

    public static JobStatus fromEntity(JobEntity jobEntity){
        JobStatus jobStatus = new JobStatus();
        jobStatus.setJobId(jobEntity.getJobId());
        jobStatus.setStatus(jobEntity.getStatus());
        jobStatus.setResultPath(jobEntity.getResultPath());
        return jobStatus;
    }

}
/*사용자가 최초로 리퀘스트 보내면 프로듀서가 응답을 줌 (처리되었다가 아니라 처리하겠다는 응답)
* 사용자가 자기의 일이 처리가 되엇는지 확인하기 위해서 잡 티켓을 받고 그 잡 아이디로 내 일이 얼만큼 되고 있는지 확인할 수 있음
* 프로듀서는 래빗에게 잡에 대한 정보를 보내고 status를 사용자에게 보냄*/