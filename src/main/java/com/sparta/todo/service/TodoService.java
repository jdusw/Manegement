package com.sparta.todo.service;

import com.sparta.todo.dto.RequestDto;
import com.sparta.todo.dto.ResponseDto;
import com.sparta.todo.entity.Todo;
import com.sparta.todo.repository.TodoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public ResponseDto findTodo(Long id) {
       Todo todo = findId(id);
       return new ResponseDto(todo);
    }

    public List<ResponseDto> findTodos() {
        return todoRepository.findAll().stream().map(ResponseDto::new).toList();
    }

    public Todo findId(Long id) {

        return todoRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("선택한 일정은 존재하지 않습니다")

        );}


}
