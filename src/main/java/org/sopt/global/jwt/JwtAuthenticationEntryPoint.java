package org.sopt.global.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.sopt.global.exception.ErrorCode;
import org.sopt.global.response.BaseErrorResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Headers", "*");

        // 요청 속성에서 예외 유형 확인
        Object exceptionType = request.getAttribute("exception");

        ErrorCode error;
        if ("TOKEN_EXPIRED".equals(exceptionType)) {
            error = ErrorCode.TOKEN_EXPIRED;  // 403 Forbidden 반환 (토큰 만료)
        }else if ("INVALID_TOKEN".equals(exceptionType)) {
            error = ErrorCode.INVALID_TOKEN; // 403 Forbidden 반환 (저장된 토큰과 다름)
        }else if("INVALID_SIGNATURE".equals(exceptionType)) {
            error = ErrorCode.INVALID_SIGNATURE;
        } else {
            error = ErrorCode.UNAUTHORIZED;  // 401 Unauthorized 반환 (기본값)
        }

        response.setStatus(error.getHttpStatus());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String json = new ObjectMapper().writeValueAsString(new BaseErrorResponse(error.getHttpStatus(), error.getMessage()));
        response.getWriter().write(json);
    }
}
