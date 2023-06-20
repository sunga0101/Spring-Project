package com.example.http.dto;

import lombok.Data;

import java.util.List;

@Data
public class ArticleWithCommentsDto {
    private String title;
    private String content;
    private WriterDto writer;
    private List<String> comment;

}

