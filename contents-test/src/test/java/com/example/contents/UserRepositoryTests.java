package com.example.contents;

import com.example.contents.entity.UserEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

// JPA를 테스트하기 위한 테스트임을 알려줌
@DataJpaTest
public class UserRepositoryTests {
    @Autowired
    private UserRepository userRepository;

    // 새 User Entity를 데이터 베이스에 추가 성공
    @Test
    @DisplayName("새 UserEntity를 데이터베이스에 추가 성공")
    public void testSaveNew() {
        // given: 테스트가 진행되기 위한 전제 조건을 준비하는 구간
        // 새로운 UserEntity 준비
        String username = "juhwan";
        UserEntity user = new UserEntity();
        user.setUsername(username);
        // when: 테스트 하고싶은 실제 기능을 작성하는 구간
        user = userRepository.save(user);
        // then: 실행한 결과가 기대한 결과와 같은지를 검증하는 구간
        // 1. 새로 반환받은 user의 id는 null이 아님
        assertNotNull(user.getId());
        // 2. 새로 반환받은 user의 username은 입력한 username과 같음
        assertEquals(username,user.getUsername());

    }
    @Test
    @DisplayName("새 UserEntity 를 데이터베이스에 추가 실패")
    public void testSaveNewFail() {
        // given: username을 가지고 UserEntity를 먼저 save
        String username = "jeeho.dev";
        UserEntity userGiven = new UserEntity();
        userGiven.setUsername(username);
        userRepository.save(userGiven);

        // when: 같은 username을 가진 새 UserEntity save 시도
        UserEntity user = new UserEntity();
        user.setUsername(username);

        // when-then: 예외 발생
        assertThrows(Exception.class, () -> userRepository.save(user));
    }

    @Test
    @DisplayName("Username으로 UserEntity 찾기")
    public void testFindByUsername() {
        // given
        UserEntity userGiven = new UserEntity();
        String username = "Kangseong.dev";
        userGiven.setUsername(username);
        userRepository.save(userGiven);

        // when
        Optional<UserEntity> userEntityOptional = userRepository.findByUsername(username);

        // then
        assertTrue(userEntityOptional.isPresent());
        assertEquals(username, userEntityOptional.get().getUsername());
    }

    // 추가 시나리오
    // username으로 찾기 실패
    // username으로 존재하는지 확인
    // id로 userEntity 삭제
}
