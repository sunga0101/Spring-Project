package com.example.article;

import com.example.article.dto.ArticleDto;
import com.example.article.entity.ArticleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<ArticleEntity, Long> {
    // 아이디가 큰 순서대로 최상위 20개
    // 1~100이라는 아이디가 있을 때

    // 100~81번의 아이디
    List<ArticleEntity> findTop20ByOrderByIdDesc();
    // 특정 id값 보다 작은 데이터 중 가장 큰 최상위 20개
    // id가 81일 때 80~61번의 아이디
    List<ArticleEntity> findTop20ByIdLessThanOrderByIdDesc(Long id);

    Page<ArticleEntity> findAllByTitleContains(String title, Pageable pageable);

}
