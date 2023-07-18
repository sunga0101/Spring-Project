package com.example.relations.repo;

import com.example.relations.entity.InstructorEntity;
import com.example.relations.entity.LectureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureRepository extends JpaRepository<LectureEntity, Long> {
}
