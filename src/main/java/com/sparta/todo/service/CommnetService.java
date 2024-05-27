package com.sparta.todo.service;

import com.sparta.todo.dto.CrequestDto;
import com.sparta.todo.dto.CresponseDto;
import com.sparta.todo.entity.Comment;
import com.sparta.todo.entity.Todo;
import com.sparta.todo.repository.CommentRepository;
import com.sparta.todo.repository.TodoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommnetService {


    private final CommentRepository commentRepository;

    private final TodoRepository todoRepository;

    public CresponseDto addComent(Long todo_id, CrequestDto crequestDto) {

        findId(todo_id);

        Todo todo = todoRepository.findById(todo_id).get();
        Comment comment = new Comment(todo,crequestDto);

        Comment saveComment = commentRepository.save(comment);
        CresponseDto cresponseDto = new CresponseDto(saveComment);

        return cresponseDto;

    }

    public Todo findId(Long id) {

        return todoRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("선택한 일정은 존재하지 않습니다")
        );}

}
