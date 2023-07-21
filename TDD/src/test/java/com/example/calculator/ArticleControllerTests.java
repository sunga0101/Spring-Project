package com.example.calculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class ArticleControllerTests {
    @InjectMocks
    private ArticleController articleController;

    @Mock
    private ArticleService articleService;
    private MockMvc mockMvc;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(articleController)
                .build();
    }

    @Test
    public void mockMvcIstNotNull() {
        assertNotNull(articleController);
        assertNotNull(mockMvc);
    }

    @Test
    public void queryArticle() throws Exception {
        // given
        String url = "/articles?title=test";
        ArticleDto articleDto = new ArticleDto();
        articleDto.setTitle("contains test");
        when(articleService.findByTitle("test"))
                .thenReturn(Collections.singletonList(articleDto));

        // when
        ResultActions resultActions = mockMvc.perform(
                get(url));

        // then
        resultActions.andExpectAll(
                status().is2xxSuccessful(),
                content().contentType(
                        MediaType.APPLICATION_JSON_VALUE),
                jsonPath("$[0].title", containsString("test"))
        );
    }
}