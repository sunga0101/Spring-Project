package com.example.article;

import com.example.article.dto.CommentDto;
import com.example.article.entity.CommentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;

    public CommentDto createComment(Long articleId, CommentDto dto) {
        if (!articleRepository.existsById(articleId))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        CommentEntity entity = new CommentEntity();
        entity.setWriter(dto.getWriter());
        entity.setContent(dto.getContent());
        entity.setArticleId(articleId);
        entity = commentRepository.save(entity);
        return CommentDto.fromEntity(entity);
    }

    // TODO 게시글 댓글 전체 조회
    public List<CommentDto> readCommentAll(Long articleId){
        List<CommentEntity> commentEntityList =this.commentRepository.findAllByArticleId(articleId);
        List<CommentDto> commentDtoList = new ArrayList<>();
        for (CommentEntity entity : commentEntityList ){
            commentDtoList.add(CommentDto.fromEntity(entity));
        }
        return commentDtoList;
    }


    // TODO 게시글 댓글 수정
    public CommentDto updateComment(Long articleId, Long commentId, CommentDto dto){
        // 코멘트 id만 있어도 가능은 하지만 잘못된 url을 보닐 수도 있으니까
        // article id도 함께 사용

        Optional<CommentEntity> optionalComment = this.commentRepository.findById(commentId);
        // 1. 요청한 댓글이 존재하는지?
        if (optionalComment.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        CommentEntity entity = optionalComment.get();

        // 2.  댓글이 대상 게시글의 댓글이 맞는지?
        if (!articleId.equals(entity.getArticleId()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        entity.setContent(dto.getContent());
        entity.setWriter(dto.getWriter());
        commentRepository.save(entity);
        return CommentDto.fromEntity(commentRepository.save(entity));

    }


    // TODO 게시글 댓글 삭제
    public void deleteComment(Long articleId, Long commentId){
        Optional<CommentEntity> optionalComment = this.commentRepository.findById(commentId);
        if (optionalComment.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        CommentEntity entity = optionalComment.get();

        if (!articleId.equals(entity.getArticleId())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        commentRepository.deleteById(commentId);

    }

}
