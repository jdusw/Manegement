package com.sparta.todo.entity;

import com.sparta.todo.dto.SignUpRequestDto;
import com.sparta.todo.jwt.UserRoleEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String nickname;

    public String username;

    public String password;

    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    private LocalDateTime createdAt;


    public User(SignUpRequestDto signUpRequestDto) {
        this.nickname = signUpRequestDto.getNickname();
        this.username = signUpRequestDto.getUsername();
        this.password = signUpRequestDto.getPassword();
        this.role = signUpRequestDto.getRole();
        this.createdAt = LocalDateTime.now();
    }

    public User() {

    }
}
