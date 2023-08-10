package com.example.securitypractice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true) // 유저네임이 없으면 안되고 하나만 있어야 한다
    private String username;
    private String password;
}
