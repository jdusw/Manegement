package com.sparta.todo.controller;

import com.sparta.todo.dto.RequestDto;
import com.sparta.todo.dto.ResponseDto;
import com.sparta.todo.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class TodoController {

    private final TodoService todoService;

    @PostMapping("/todo")
    public ResponseDto postTodo(@RequestBody RequestDto requestDto){
        return todoService.createTodo(requestDto);
    }
}
