package com.sparta.todo.dto;

import com.sparta.todo.entity.Todo;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class RequestDto {
    private String title;
    private String content;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
