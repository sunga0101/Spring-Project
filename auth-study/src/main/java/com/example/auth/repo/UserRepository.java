package com.example.auth.repo;

import com.example.auth.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<UserEntity,Long> {
    // UserDetailsManager 를 구현하기 위한 메소드 추가
    Optional<UserEntity> findByUsername(String username);
    boolean existsByUsername(String username);

}
