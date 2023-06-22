package com.example.article;

import com.example.article.dto.ArticleDto;
import com.example.article.entity.ArticleEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
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
    // 사용자가 원하는 갯수만큼, 원하는 페이지시퀀스 보여주기
    @GetMapping
    public Page<ArticleDto> readAll(
            @RequestParam("limit") Integer limit, @RequestParam(value = "page", defaultValue = "0") Integer page){

        return service.readArticlePaged(limit,page);
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
            @PathVariable("id") Long id    ) { service.deleteArticle(id);
    }

//    @GetMapping("/page-test")
//    public Page<ArticleDto> readPageTest(){
//        return this.service.readArticlePaged();
//    }
}
