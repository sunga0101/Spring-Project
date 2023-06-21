package com.example.article;

import com.example.article.dto.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
}
