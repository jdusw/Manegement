package com.sparta.todo.service;

import com.sparta.todo.dto.SignUpRequestDto;
import com.sparta.todo.entity.User;
import com.sparta.todo.exception.NotFound;
import com.sparta.todo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SignUpService {

    private final UserRepository userRepository;

    public ResponseEntity<String> sign(SignUpRequestDto signUpRequestDto) {
        String username = signUpRequestDto.getUsername();
        User user = new User(signUpRequestDto);

        Optional<User> checkUsername = userRepository.findByUsername(username);
        if (checkUsername.isPresent()) {
            throw new NotFound("중복된 사용자가 존재합니다.");
        }

        userRepository.save(user);

        String message = "회원 등록에 성공했습니다";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Status-Success", "Success");

        return ResponseEntity.status(HttpStatus.OK)
                .headers(headers)
                .body(message);
    }

}
