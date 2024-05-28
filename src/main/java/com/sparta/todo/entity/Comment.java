package com.sparta.todo.entity;

import com.sparta.todo.dto.ComRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long commentId;
    private String content;
    private String userId;
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "todo_id")
    private Todo todo;

    public Comment(Todo todo, ComRequestDto comRequestDto) {
        this.todo = todo;
        this.content = comRequestDto.getContent();
        this.userId = comRequestDto.getUserId();
        this.createdAt = LocalDateTime.now();
    }

    public Comment() {
    }


    public void update(Todo todo, String content) {
        this.todo = todo;
        this.content = content;
    }
}
