package com.example.contents;

import com.example.contents.dto.UserDto;
import com.example.contents.entity.UserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    @DisplayName("UserDto로 createUser")
    public void testCreateUser(){
        // given 주어진 상황은?
        // 1. UserRepository가 전달받을 UserEntity 정의
        String username = "lion.dev";
        UserEntity userEntityIn = new UserEntity();
        userEntityIn.setUsername(username);

        // 2. UserRepository가 반환할 UserEntity 정의
        Long userId = 1L;
        UserEntity userEntityOut = new UserEntity();
        userEntityOut.setId(userId);
        userEntityOut.setUsername(username);

        // 이렇게 기능해달라고 가정. 이외 나머지는 내가 테스트하겠다!
        when(userRepository.save(userEntityIn))
                .thenReturn(userEntityOut);
        when(userRepository.existsByUsername(username))
                .thenReturn(false);
        // when
        UserDto user = new UserDto();
        user.setUsername(username);
        UserDto result = userService.createUser(user);


        // then
        Assertions.assertEquals(result.getId(), userId);
        Assertions.assertEquals(result.getUsername(), username);
    }
}
