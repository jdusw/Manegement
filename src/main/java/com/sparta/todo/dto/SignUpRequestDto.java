package com.sparta.todo.dto;

import com.sparta.todo.jwt.UserRoleEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SignUpRequestDto {

    public String nickname;

    @NotBlank
    @Pattern(regexp =  "^(?=.*[0-9])(?=.*[a-z])(?=\\S+$).{4,10}$",
            message = "최소 4자 이상, 10자 이하이며 알파벳 소문자(a~z), 숫자(0~9)")
    public String username;

    @NotBlank
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=\\S+$).{8,15}",
            message = "최소 8자 이상, 15자 이하이며 알파벳 대소문자(a~z, A~Z), 숫자(0~9)")
    public String password;

    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    private LocalDateTime createdAt;
}
