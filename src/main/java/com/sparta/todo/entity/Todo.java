package com.sparta.todo.entity;

import com.sparta.todo.dto.RequestDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_id", nullable = false)
    private Long TodoId;

    private String title;

    private String content;

    private String userName;

    private String password;

    private LocalDateTime createdAt;

    public Todo(RequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.userName = requestDto.getUserName();
        this.password = requestDto.getPassword();
        this.createdAt = LocalDateTime.now();
    }


}
