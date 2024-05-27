package com.sparta.todo.entity;

import com.sparta.todo.dto.CrequestDto;
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

    public Comment(Todo todo, CrequestDto crequestDto) {
        this.todo = todo;
        this.content = crequestDto.getContent();
        this.userId = crequestDto.getUserId();
        this.createdAt = LocalDateTime.now();
    }

}
