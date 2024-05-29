package com.sparta.todo.controller;

import com.sparta.todo.dto.SignUpRequestDto;
import com.sparta.todo.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class SignUpController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@Valid @RequestBody SignUpRequestDto signUpRequestDto) {
        return userService.sign(signUpRequestDto);
    }

}
