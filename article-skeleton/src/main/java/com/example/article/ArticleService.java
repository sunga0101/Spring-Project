package com.example.article;

import com.example.article.dto.ArticleDto;
import com.example.article.entity.ArticleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    /*
    public List<ArticleDto> readArticlePaged(){
        // JPA Query Method 방식 (비추천)
        List<ArticleDto> articleDtoList = new ArrayList<>();
        for ( ArticleEntity entity : this.repository.findTop20ByOrderByIdDesc())
        {
            articleDtoList.add(ArticleDto.fromEntity(entity));
        }
        return articleDtoList;
    }
    */

    public Page<ArticleDto> readArticlePaged(Integer limit, Integer page){
        // PagingAndSortingRepository 메소드에 전달하는 용도
        // 조회하고 싶은 페이지의 정보를 담는 객체
        //
        Pageable pageable = PageRequest.of( page, limit, Sort.by("id").descending()); //20개씩 데이터를 나눌 때 0번 페이지를 주세요! 하는 요청
        Page<ArticleEntity> articleEntityPage = repository.findAll(pageable);

        // map: 전달받은 함수를 각 원소에 인자로 전달한 결과를
        // 다시 모아서 Stream으로
        // Page.map: 전달받은 함수를 각 원소에 인자로 전달한 결과를 다시 모아 Page로
        Page<ArticleDto> articleDtoList = articleEntityPage.map(ArticleDto::fromEntity);
        return articleDtoList;

    }

    public Page<ArticleDto> search(String query, Integer page){

        Pageable pageable = PageRequest.of(page,20,Sort.by("id").descending());
        return repository.findAllByTitleContains(query,pageable).map(ArticleDto::fromEntity);

    }
}
