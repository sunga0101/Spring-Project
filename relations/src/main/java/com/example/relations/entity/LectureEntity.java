package com.example.relations.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Time;

@Data
@Entity
//@Table(name = "lecture")
public class LectureEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String day;
    private Time startTime; // LocalTime, Instant 등 가능능
    private Integer endTime;

    @ManyToOne // 수업 여러 개에 강의자 한명
    @JoinColumn(name = "instructor") // instructor라는 이름으로  join컬럼 만듦
    private InstructorEntity instructor; // 기본은 insturctor_id
}
