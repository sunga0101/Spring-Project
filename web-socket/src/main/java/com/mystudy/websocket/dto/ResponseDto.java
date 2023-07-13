package com.mystudy.websocket.dto;

public class ResponseDto {
    private String content;

    public ResponseDto(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
