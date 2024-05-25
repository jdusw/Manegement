package com.sparta.todo.dto;

import com.sparta.todo.entity.Todo;
import lombok.Getter;

@Getter
public class RequestDto {
    private String title;
    private String content;
    private String userName;
    private String password;
}
