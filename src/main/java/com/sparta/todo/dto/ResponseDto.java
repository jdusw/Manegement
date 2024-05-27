package com.sparta.todo.dto;

import com.sparta.todo.entity.Comment;
import com.sparta.todo.entity.Todo;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ResponseDto {
    private Long id;
    private String title;
    private String content;
    private String userName;
    private LocalDateTime createAt;

    public ResponseDto(Todo todo) {
        this.id = todo.getId();
        this.title = todo.getTitle();
        this.content = todo.getContent();
        this.userName = todo.getUserName();
        this.createAt = LocalDateTime.now();
    }
}

