package com.sparta.todo.controller;

import com.sparta.todo.dto.TestRequestDto;
import com.sparta.todo.jwt.JwtUtil;
import com.sparta.todo.jwt.UserRoleEnum;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
public class JwpController {

    private final JwtUtil jwtUtil;

    //토큰 발급
    @PostMapping("/test")
    public String testJwp(@RequestBody TestRequestDto testRequestDto) throws Exception{
        if (testRequestDto.getUserName().equals("Hi") && testRequestDto.getPassword().equals("1234")) {
            String token = jwtUtil.createToken("Hi", UserRoleEnum.USER);

            log.info("Token generated successfully.");
            return "createToken : " + token;
        }else{
            throw new Exception("아이디와 비밀번호가 일치하지 않습니다. ");
        }
    }

}
