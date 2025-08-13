package org.sopt.global.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.sopt.global.exception.ErrorCode;
import org.sopt.global.response.BaseErrorResponse;
import org.springframework.http.MediaType;
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

        Object exceptionType = request.getAttribute("exception");

        ErrorCode error;
        if ("TOKEN_EXPIRED".equals(exceptionType)) {
            error = ErrorCode.TOKEN_EXPIRED;
        }else if ("INVALID_TOKEN".equals(exceptionType)) {
            error = ErrorCode.INVALID_TOKEN;
        }else if("INVALID_SIGNATURE".equals(exceptionType)) {
            error = ErrorCode.INVALID_SIGNATURE;
        } else {
            error = ErrorCode.UNAUTHORIZED;
        }

        setErrorResponse(response, error);
    }

    private void setErrorResponse(HttpServletResponse response, ErrorCode error) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        response.setCharacterEncoding("UTF-8");
        response.setStatus(error.getHttpStatus());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        BaseErrorResponse errorResponse = new BaseErrorResponse(error);
        try {
            response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
