package com.sparta.todo.service;

import com.sparta.todo.dto.LoginRequestDto;
import com.sparta.todo.dto.SignUpRequestDto;
import com.sparta.todo.entity.User;
import com.sparta.todo.exception.NotFound;
import com.sparta.todo.jwt.JwtUtil;
import com.sparta.todo.jwt.UserRoleEnum;
import com.sparta.todo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private JwtUtil jwtUtil;

    private final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";


    public void sign(SignUpRequestDto signUpRequestDto) {
        String username = signUpRequestDto.getUsername();
        String password = signUpRequestDto.getPassword();
        String nickname = signUpRequestDto.getNickname();

        Optional<User> checkUsername = userRepository.findByUsername(username);
        if (checkUsername.isPresent()) {
            throw new NotFound("중복된 사용자가 존재합니다.");
        }

        // 사용자 ROLE 확인
        UserRoleEnum role = UserRoleEnum.USER;
        if (signUpRequestDto.isAdmin()) {
            if (!ADMIN_TOKEN.equals(signUpRequestDto.getAdminToken())) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRoleEnum.ADMIN;
        }


        User user = new User(nickname, username, password, role);
        userRepository.save(user);

    }


    public ResponseEntity<String> login(LoginRequestDto loginRequestDto) {

        // 사용자 확인
        userRepository.findByUsernameAndPassword(loginRequestDto.getUsername(), loginRequestDto.getPassword()).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );


        String token = jwtUtil.createToken(loginRequestDto.getUsername(), UserRoleEnum.USER);

        String message = "로그인에 성공했습니다";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", token);

        return new ResponseEntity<>(message, headers, HttpStatus.OK);

        }

    }

