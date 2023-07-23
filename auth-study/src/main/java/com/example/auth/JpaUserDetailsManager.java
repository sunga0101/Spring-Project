package com.example.auth;

import com.example.auth.entity.CustomUserDetails;
import com.example.auth.entity.UserEntity;
import com.example.auth.repo.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Slf4j
@Service
public class JpaUserDetailsManager implements UserDetailsManager {
    private final UserRepository userRepository;

    public JpaUserDetailsManager(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        // 테스트용 사용자 추가
        createUser(User.withUsername("user")
                .password(passwordEncoder
                .encode("asdf"))
                .build());
    }


    // 로그인 회원가입을 위해 반드시 필수적인 메소드들만 구현해보도록 한다
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> user =  userRepository.findByUsername(username);
        if (user.isEmpty())
            throw new UsernameNotFoundException(username);
//        return User.withUsername(username).build();
        return CustomUserDetails.fromEntity(user.get());
    }

    @Override
    public void createUser(UserDetails user) {
        if (userExists(user.getUsername())) // 이미 존재
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
//        UserEntity newUser = new UserEntity();
//        newUser.setUsername(user.getUsername());
//        newUser.setPassword(user.getPassword());
//        userRepository.save(newUser);

        try {
            userRepository.save(
                    ((CustomUserDetails) user).newEntity());
        } catch ( ClassCastException e) {
            log.error("failed to cast to {}", CustomUserDetails.class);
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public boolean userExists(String username) {
        return (userRepository.existsByUsername(username));
    }



    // -------- 추후 개발 --------
    @Override
    public void updateUser(UserDetails user) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public void deleteUser(String username) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }

}
