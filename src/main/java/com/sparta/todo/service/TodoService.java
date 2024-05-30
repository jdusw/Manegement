package com.sparta.todo.service;

import com.sparta.todo.dto.RequestDto;
import com.sparta.todo.dto.ResponseDto;
import com.sparta.todo.entity.Todo;
import com.sparta.todo.entity.User;
import com.sparta.todo.exception.NotFound;
import com.sparta.todo.jwt.JwtUtil;
import com.sparta.todo.repository.TodoRepository;
import com.sparta.todo.repository.UserRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@AllArgsConstructor
public class TodoService {

    private TodoRepository todoRepository;
    private final UserRepository UserRepository;

    private final JwtUtil jwtUtil;

    public ResponseDto createTodo(RequestDto requestDto){
        Todo todo = new Todo(requestDto);

        Todo saveTodo = todoRepository.save(todo);
        ResponseDto responseDto = new ResponseDto(saveTodo);

        return responseDto;
    }

    public ResponseDto findTodo(Long id) {
       Todo todo = getTodo(id);
       return new ResponseDto(todo);
    }

    public List<ResponseDto> findTodos() {
        return todoRepository.findAll().stream().map(ResponseDto::new).toList();
    }

    @Transactional
    public ResponseDto update(Long id, RequestDto requestDto, HttpServletRequest request) {
        Todo todo = getTodo(id);

        User user = getUserFromToken(request);

        if(!requestDto.getUsername().equals(user.getUsername())){
            throw new NotFound("작성자만 수정할 수 있습니다");
        }

        return new ResponseDto(todo);

    }

    public ResponseDto delete(Long id, RequestDto requestDto, HttpServletRequest request) {
        Todo todo = getTodo(id);

        User user = getUserFromToken(request);

        if(!requestDto.getUsername().equals(user.getUsername())){
            throw new NotFound("작성자만 삭제할 수 있습니다");
        }

        return new ResponseDto(todo);
    }

    public Todo getTodo(Long id) {
        return todoRepository.findById(id).orElseThrow(()->
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
            return UserRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("해당 사용자를 찾을 수 없습니다.")
            );
        }
        throw new IllegalArgumentException("token 이 없습니다.");
    }



}
