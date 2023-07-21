package com.example.calculator;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ArticleServiceTests {
    @InjectMocks
    private ArticleService articleService; // 테스트 하고자 하는 것

    @Mock
    private ArticleRepository articleRepository;

    @Test // 실패 테스트 작성
    public void searchByTitle() {
        // given
        String query = "Title";
        Article found = new Article();
        found.setId(1L);
        found.setTitle("Title here");
        found.setContent("Test Content");
        when(articleRepository.findAllByTitleContains(query))
                .thenReturn(Collections.singletonList(found));

        // when
        List<ArticleDto> articleDtoList  // null이 된다
                = articleService.findByTitle(query);

        // then
        assertEquals(1, articleDtoList.size());
        assertTrue(articleDtoList.get(0).getTitle().contains(query));
    }
}