package com.sparta.todo.dto;

import com.sparta.todo.entity.Todo;
import lombok.Getter;

@Getter
public class ResponseDto {
    private Long id;
    private String title;
    private String content;
    private String userName;

    public ResponseDto(Todo todo) {
        this.id = todo.getId();
        this.title = todo.getTitle();
        this.content = todo.getContent();
        this.userName = todo.getUserName();
    }
}

