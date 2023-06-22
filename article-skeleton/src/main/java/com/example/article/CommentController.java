package com.example.article;

import com.example.article.dto.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articles/{id}/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    //Post
    @PostMapping
    public CommentDto createComment(
            @PathVariable("id") Long articleId,
            @RequestBody CommentDto dto){
        return commentService.createComment(articleId, dto);
    }

    // TODO 게시글 댓글 전체 조회
    @GetMapping
    public List<CommentDto> readAll(@PathVariable("id") Long articleId){
        return this.commentService.readCommentAll(articleId);
    }

    // TODO 게시글 댓글 수정
    @PutMapping("/{commentId}")
    public CommentDto update(@PathVariable("id") Long articleId,
                             @PathVariable("commentId") Long commentId,
                             @RequestBody CommentDto dto){ // 전달받은 객체
        return this.commentService.updateComment(articleId,commentId,dto);
    }

    // TODO 게시글 댓글 삭제
    @DeleteMapping("{commentId}")
    public void delete(@PathVariable("id") Long articleId,
                       @PathVariable("commentId") Long commentId){
        this.commentService.deleteComment(articleId, commentId);
    }
}
