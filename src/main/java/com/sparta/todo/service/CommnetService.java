package com.sparta.todo.service;

import com.sparta.todo.dto.CrequestDto;
import com.sparta.todo.dto.CresponseDto;
import com.sparta.todo.dto.ResponseDto;
import com.sparta.todo.entity.Comment;
import com.sparta.todo.entity.Todo;
import com.sparta.todo.repository.CommentRepository;
import com.sparta.todo.repository.TodoRepository;
import jakarta.transaction.Transactional;
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

    @Transactional
    public CresponseDto update(Long todo_id, Long comment_id, CrequestDto crequestDto) {
        findId(todo_id);
        findById_c(comment_id);

        Todo todo = todoRepository.findById(todo_id).get();

        Comment comment = commentRepository.findById(comment_id).get();
        if (!comment.getUserId().equals(crequestDto.getUserId())) {
            throw new IllegalArgumentException("사용자 아이디가 일치하지 않습니다.");
        }
        comment.update(todo, crequestDto.getContent());

        return new CresponseDto(comment);
    }


    public Comment findById_c(Long Id) {
        return commentRepository.findById(Id).orElseThrow(()->
                new IllegalArgumentException("선택한 댓글은 존재하지 않습니다")
        );}


    public Todo findId(Long Id) {

        return todoRepository.findById(Id).orElseThrow(()->
                new IllegalArgumentException("선택한 일정은 존재하지 않습니다")
        );}


}
