package com.sparta.todo.service;

import com.sparta.todo.dto.ComRequestDto;
import com.sparta.todo.dto.ComResponseDto;
import com.sparta.todo.entity.Comment;
import com.sparta.todo.entity.Todo;
import com.sparta.todo.entity.User;
import com.sparta.todo.exception.NotFound;
import com.sparta.todo.jwt.JwtUtil;
import com.sparta.todo.repository.CommentRepository;
import com.sparta.todo.repository.TodoRepository;
import com.sparta.todo.repository.UserRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@AllArgsConstructor
public class CommnetService {


    private final CommentRepository commentRepository;
    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    private JwtUtil jwtUtil;

    public ComResponseDto addComent(Long todo_id, ComRequestDto comRequestDto) {

        getTodo(todo_id);

        Todo todo = todoRepository.findById(todo_id).get();
        Comment comment = new Comment(todo, comRequestDto);

        Comment saveComment = commentRepository.save(comment);
        ComResponseDto comResponseDto = new ComResponseDto(saveComment);

        return comResponseDto;

    }

    @Transactional
    public ComResponseDto update(Long todo_id, Long comment_id, ComRequestDto comRequestDto, HttpServletRequest request) {

        getTodo(todo_id);

        User user = getUserFromToken(request);

        Comment comment = commentRepository.findById(comment_id).get();
        Todo todo = todoRepository.findById(todo_id).get();

        if(!comRequestDto.getUserId().equals(user.getUsername())){
            throw new NotFound("작성자만 수정할 수 있습니다");
        }

        comment.update(todo, comRequestDto.getContent());


        return new ComResponseDto(comment);
    }

    public void delete(Long todoId, Long comment_id, ComRequestDto comRequestDto, HttpServletRequest request) {
        getTodo(todoId);
        getComment(comment_id);

        User user = getUserFromToken(request);

        Comment comment = commentRepository.findById(comment_id).get();

        if(!comRequestDto.getUserId().equals(user.getUsername())){
            throw new NotFound("작성자만 삭제할 수 있습니다");
        }
        commentRepository.delete(comment);
    }


    public Comment getComment(Long Id) {
        return commentRepository.findById(Id).orElseThrow(()->
                new NotFound("선택한 댓글은 존재하지 않습니다")
        );}


    public Todo getTodo(Long Id) {

        return todoRepository.findById(Id).orElseThrow(()->
                new NotFound("선택한 일정은 존재하지 않습니다")
        );}


    public User getUserFromToken(HttpServletRequest request) {
        String token = jwtUtil.getJwtFromHeader(request);

        Claims claims;

        if (StringUtils.hasText(token)) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("올바른 token 이 아닙니다.");
            }
            return userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("해당 사용자를 찾을 수 없습니다.")
            );
        }
        throw new IllegalArgumentException("token 이 없습니다.");
    }
    }

