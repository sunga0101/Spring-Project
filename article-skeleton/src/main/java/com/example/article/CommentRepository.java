package com.example.article;

import com.example.article.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    // 기능 추가
    // ArticleId가 id인 CommentEntity만 반환하도록
    List<CommentEntity> findAllByArticleId(Long id);
}
