package com.sparta.todo.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
public class ComRequestDto {

    @NotBlank
    private String content;

    private String userId;
}
