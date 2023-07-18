package com.example.relations.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "insturctor")
public class InstructorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
}
