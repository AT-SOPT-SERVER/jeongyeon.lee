package org.sopt.common.exception;

import org.sopt.common.response.BaseErrorResponse;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<BaseErrorResponse> handleCustomExceptions(CustomException e) {
        return new ResponseEntity<>(new BaseErrorResponse(e.getErrorCode()), HttpStatusCode.valueOf(e.getErrorCode().getHttpStatus()));
    }
}
