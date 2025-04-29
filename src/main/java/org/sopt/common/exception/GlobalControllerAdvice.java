package org.sopt.common.exception;

import org.sopt.common.response.BaseErrorResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<BaseErrorResponse> handleCustomExceptions(CustomException e) {
        return new ResponseEntity<>(new BaseErrorResponse(e.getErrorCode()), HttpStatusCode.valueOf(e.getErrorCode().getHttpStatus()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getFieldErrors()
                .stream()
                .findFirst()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .orElse("입력값이 잘못되었습니다.");

        BaseErrorResponse response = new BaseErrorResponse(
                false,
                400,
                message,
                java.time.LocalDateTime.now()
        );

        return new ResponseEntity<>(response, HttpStatusCode.valueOf(400));
    }
}
