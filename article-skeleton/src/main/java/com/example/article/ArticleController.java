package com.example.article;

import com.example.article.dto.ArticleDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/articles")
@RequiredArgsConstructor // final이 붙은 인자를 기준으로 하는 생성자 -> spring이 의존성 주입해줌 -> 에러 없어짐
public class ArticleController {
    private final ArticleService service;

    // POST /articles
    @PostMapping
    public ArticleDto create(@RequestBody ArticleDto dto){
        return this.service.createArticle(dto);
    }

    // GET /articles
    @GetMapping
    public List<ArticleDto> readAll(){
        return service.readArticleAll();
    }

    // GET /articles/{id}
    @GetMapping("/{id}")
    public ArticleDto read(@PathVariable("id") Long id){
        return this.service.readArticle(id);
    }

    // PUT /articles/{id}
    @PutMapping("/{id}")
    public ArticleDto update(@PathVariable("id") Long id, @RequestBody ArticleDto dto){
        return this.service.updateArticle(id,dto);
    }


    // DELETE /articles/{id}
    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable("id") Long id
    ) {
        service.deleteArticle(id);
    }

}
