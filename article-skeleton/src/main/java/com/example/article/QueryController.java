package com.example.article;

import com.example.article.dto.ArticleDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class QueryController {
//    TODO GET /path?query=keyword&limit=20
//    http://localhost:8080/path?query=hello&limit=2023
    final private ArticleService articleService;
    @GetMapping("/path")
    public Map<String, Object> queryParams(
            @RequestParam(value = "query", defaultValue = "defalut") String query,
            @RequestParam(value = "limit", required = false) Integer limit
    ){
        log.info("query ="+ query);
        log.info("limit ="+ limit);
        Map<String, Object> response = new HashMap<>();
        response.put("query",query);
        response.put("limit",limit);
        return response;
    }

    @GetMapping("/search")
    public Page<ArticleDto> search(
            @PathVariable("search")
            @RequestParam String query,
            @RequestParam(value = "page", defaultValue = "0") Integer page){
        return articleService.search(query,page);

    }


}
