package com.example.relations.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;

    @ManyToMany
    @JoinTable(name = "student_attending_lecture")
    // 생성되는 조인 테이블 이름 변경할 수 있음
    private List<LectureEntity> attending;

}
