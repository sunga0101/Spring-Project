package com.example.relations.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "lecture")
public class LectureEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String courseName;
    private String day;
    private Integer startTime;
    private Integer endTime;
}
