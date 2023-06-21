package com.example.article.dto;

import com.example.article.entity.CommentEntity;
import lombok.Data;

@Data
public class CommentDto {
    private Long id;
    private Long articleId;
    private String writer;
    private String content;

    //정적 팩토리 메소드 패턴
    public static CommentDto fromEntity(CommentEntity entity){
        CommentDto dto = new CommentDto();
        dto.setWriter(entity.getWriter());
        dto.setContent(entity.getContent());
        return dto;
    }

}
