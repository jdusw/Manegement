package com.sparta.todo.dto;

import com.sparta.todo.entity.Comment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class CresponseDto {

    private Long id;
    private String content;
    private String userId;
    private Long todoId ;
    private LocalDateTime createdAt;

    public CresponseDto(Comment comment){
        this.id = comment.getCommentId();
        this.content = comment.getContent();
        this.userId = comment.getUserId();
        this.todoId = comment.getTodo().getId();
        this.createdAt = comment.getCreatedAt();
    }


}
