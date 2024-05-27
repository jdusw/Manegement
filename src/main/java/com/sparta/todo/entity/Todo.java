package com.sparta.todo.entity;

import com.sparta.todo.dto.RequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "todos")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String title;

    private String content;

    private String userName;

    private String password;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "todo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    public Todo(RequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.userName = requestDto.getUserName();
        this.password = requestDto.getPassword();
        this.createdAt = LocalDateTime.now();
    }


    public Todo() {
    }

    public void updateTodo(RequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.userName = requestDto.getUserName();
        this.createdAt = LocalDateTime.now();
    }
}
