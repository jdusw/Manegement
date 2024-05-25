package com.sparta.todo.controller;

import com.sparta.todo.dto.RequestDto;
import com.sparta.todo.dto.ResponseDto;
import com.sparta.todo.service.TodoService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class TodoController {

    private final TodoService todoService;

    @PostMapping("/todo")
    public ResponseDto postTodo(@RequestBody RequestDto requestDto){
        return todoService.createTodo(requestDto);
    }

    @GetMapping("/todo/{id}")
    public ResponseDto getTodo(@PathVariable Long id){
        return todoService.findTodo(id);
    }

    @GetMapping("/todo")
    public List<ResponseDto> getTodos(){
        return todoService.findTodos();
    }

    @PutMapping("/todo/{id}")
    public ResponseDto updateTodo(@PathVariable Long id, @RequestBody RequestDto requestDto){
        return todoService.update(id,requestDto);
    }

    @DeleteMapping("/todo/{id}")
    public ResponseDto deleteTodo(@PathVariable Long id, @RequestBody RequestDto requestDto){
        return todoService.delete(id,requestDto);
    }


}
