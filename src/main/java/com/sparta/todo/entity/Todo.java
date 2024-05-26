package com.sparta.todo.entity;

import com.sparta.todo.dto.RequestDto;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "todos")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_id", nullable = false)
    private Long Id;

    private String title;

    private String content;

    private String userName;

    private String password;

    private LocalDateTime createdAt;

    @OneToMany
    @JoinColumn(name = "todos_id")
    private List<Comment> commentList = new ArrayList<>();

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
