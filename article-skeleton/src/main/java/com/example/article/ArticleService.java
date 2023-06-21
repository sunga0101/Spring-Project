package com.example.article;

import com.example.article.dto.ArticleDto;
import com.example.article.entity.ArticleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository repository;

    public ArticleDto createArticle(ArticleDto dto) {
        ArticleEntity newArticle = new ArticleEntity();
        newArticle.setTitle(dto.getTitle());
        newArticle.setContent(dto.getContent());
        newArticle.setWriter(dto.getWriter());
        newArticle = this.repository.save(newArticle);
        return ArticleDto.fromEntity(newArticle);
    }

    public ArticleDto readArticle(Long id) {
        Optional<ArticleEntity> entity = this.repository.findById(id);
        if (entity.isPresent()) {
            return ArticleDto.fromEntity(entity.get());
        }
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND);

    }

    public List<ArticleDto> readArticleAll() {
        List<ArticleDto> articleList = new ArrayList<>();
        for (ArticleEntity entity: repository.findAll()) {
            articleList.add(ArticleDto.fromEntity(entity));
        }
        return articleList;
    }

    public ArticleDto updateArticle(Long id, ArticleDto dto) {
        Optional<ArticleEntity> entity = this.repository.findById(id);
        if (entity.isPresent()){
            ArticleEntity newArticle = entity.get();
            newArticle.setTitle(dto.getTitle());
            newArticle.setWriter(dto.getWriter());
            newArticle.setContent(dto.getContent());
            this.repository.save(newArticle);
            return ArticleDto.fromEntity(newArticle);
        }
        else throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }

    public void deleteArticle(Long id) {
        if (repository.existsById(id))
            repository.deleteById(id);
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND);

    }
}
