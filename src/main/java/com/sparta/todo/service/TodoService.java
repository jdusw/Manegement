package com.sparta.todo.service;

import com.sparta.todo.dto.RequestDto;
import com.sparta.todo.dto.ResponseDto;
import com.sparta.todo.entity.Todo;
import com.sparta.todo.repository.TodoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TodoService {

    private TodoRepository todoRepository;


    public ResponseDto createTodo(RequestDto requestDto){
        Todo todo = new Todo(requestDto);
        Todo saveTodo = todoRepository.save(todo);
        ResponseDto responseDto = new ResponseDto(saveTodo);

        return responseDto;
    }

}
