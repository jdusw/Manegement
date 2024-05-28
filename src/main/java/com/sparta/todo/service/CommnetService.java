package com.sparta.todo.service;

import com.sparta.todo.dto.ComRequestDto;
import com.sparta.todo.dto.ComResponseDto;
import com.sparta.todo.entity.Comment;
import com.sparta.todo.entity.Todo;
import com.sparta.todo.exception.NotFound;
import com.sparta.todo.repository.CommentRepository;
import com.sparta.todo.repository.TodoRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommnetService {


    private final CommentRepository commentRepository;

    private final TodoRepository todoRepository;

    public ComResponseDto addComent(Long todo_id, ComRequestDto comRequestDto) {

        findId(todo_id);

        Todo todo = todoRepository.findById(todo_id).get();
        Comment comment = new Comment(todo, comRequestDto);

        Comment saveComment = commentRepository.save(comment);
        ComResponseDto comResponseDto = new ComResponseDto(saveComment);

        return comResponseDto;

    }

    @Transactional
    public ComResponseDto update(Long todo_id, Long comment_id, ComRequestDto comRequestDto) {
        findId(todo_id);
        findById_c(comment_id);

        Todo todo = todoRepository.findById(todo_id).get();

        Comment comment = commentRepository.findById(comment_id).get();

        if (!comment.getUserId().equals(comRequestDto.getUserId())) {
            throw new NotFound("사용자 아이디가 일치하지 않습니다.");
        }
        comment.update(todo, comRequestDto.getContent());

        return new ComResponseDto(comment);
    }

    public ResponseEntity<String> delete(Long todoId, Long comment_id, ComRequestDto comRequestDto) {
        findId(todoId);
        findById_c(comment_id);

        Comment comment = commentRepository.findById(comment_id).get();

        if (!comment.getUserId().equals(comRequestDto.getUserId())) {
            throw new NotFound("사용자 아이디가 일치하지 않습니다.");
        }

        commentRepository.delete(comment);

        String message = "삭제에 성공했습니다";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Status-Success", "Success");

        return ResponseEntity.status(HttpStatus.OK)
                                .headers(headers)
                                .body(message);

    }


    public Comment findById_c(Long Id) {
        return commentRepository.findById(Id).orElseThrow(()->
                new NotFound("선택한 댓글은 존재하지 않습니다")
        );}


    public Todo findId(Long Id) {

        return todoRepository.findById(Id).orElseThrow(()->
                new NotFound("선택한 일정은 존재하지 않습니다")
        );}


}
