package com.example.relations.repo;

import com.example.relations.entity.InstructorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<InstructorEntity, Long> {
}
