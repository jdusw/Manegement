package com.sparta.todo.filter;

import com.sparta.todo.jwt.JwtUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Component
@Slf4j
public class AuthFilter implements Filter {

    private final JwtUtil jwtUtil;

    public AuthFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        log.info("doFilter() called.");

        String token = jwtUtil.getTokenFromRequest(httpServletRequest);
        log.info("token from filter: {}", token);

        if (StringUtils.hasText(token)) { // 토큰이 존재하면 검증 시작

            try {
                // 토큰 검증
                if (!jwtUtil.validateToken(token)) {
                    httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Token");
                    return;
                }

                // 토큰이 유효하면 필터 체인을 계속 진행
                chain.doFilter(request, response);
            } catch (IllegalArgumentException e) {
                httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token Error: " + e.getMessage());
            }

        } else {
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token Not Found");
        }
    }
}


