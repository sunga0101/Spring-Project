package com.example.calculator;
// Article Repository가 자동으로 의존성 주입이 되는지 알아보자

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
public class ArticleRepositoryTests {
    @Autowired
    private ArticleRepository articleRepository;

    @Test
    // articleRepository를 생성하여 데이터베이스 소통의 창구를 마련
    public void articleRepositoryIsNotNull() {
        assertThat(articleRepository).isNotNull();
    }

    @Test
    @DisplayName("데이터베이스에 저장하는 기능에 대한 테스트")
    public void createArticle() {
        // given
        Article article = new Article();
        article.setTitle("Hello!");
        article.setContent("TDD is hard but useful");

        // when
        Article result = articleRepository.save(article);

        // then
        assertThat(result.getId()).isNotNull();
        assertEquals(result.getTitle(), "Hello!");
        assertEquals(result.getContent(), "TDD is hard but useful");
    }

    @Test
    @DisplayName("제목을 기준으로 Article 데이터를 조회하는 요구사항")
    public void findByTitleContains() {
        // given
        Article article = new Article();
        article.setTitle("Hello");
        article.setContent("Lorem Ipsum");
        articleRepository.save(article);
        article = new Article();
        article.setTitle("He");
        articleRepository.save(article);

        // when
        List<Article> result1 = articleRepository.findAllByTitleContains("He");
        List<Article> result2 = articleRepository.findAllByTitleContains("Hel");

        // then
        assertEquals(2, result1.size());
        assertEquals(1, result2.size());
    }

}