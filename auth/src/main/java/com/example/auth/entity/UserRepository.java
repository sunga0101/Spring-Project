package com.example.auth.entity;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

// entity 폴더 안에 두는 이유? entity 바로 옆에 있으면 함께 다루기도 쉽고 직관적이어서

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    // 1. 사용자 계쩡이름으로 사용자 정보를 회수하는 기능
    Optional<UserEntity> findByUsername(String username);
    // 2. 사용자 계정이름을 가진 사용자 정보가 존재하는지 판단하는 기능
    Boolean existsByUsername(String username);
}