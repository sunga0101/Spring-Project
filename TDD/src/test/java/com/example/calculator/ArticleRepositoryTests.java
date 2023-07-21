package com.example.calculator;
// Article Repository가 자동으로 의존성 주입이 되는지 알아보자

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.*;



@DataJpaTest
public class ArticleRepositoryTests {
    @Autowired
    private ArticleRepository articleRepository;

    @Test
    // articleRepository를 생성하여 데이터베이스 소통의 창구를 마련
    public void articleRepositoryIsNotNull() {
        assertThat(articleRepository).isNotNull();
    }
}