package org.sopt.common.exception;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public enum ErrorCode {
    //common
    METHOD_NOT_ALLOWED(100, HttpStatus.METHOD_NOT_ALLOWED.value(), "유효하지 않은 Http 메서드입니다."),
    API_NOT_FOUND(101, HttpStatus.NOT_FOUND.value(), "존재하지 않는 API 입니다."),

    //Post
    INVALID_TITLE_LENGTH(201, BAD_REQUEST.value(), "제목은 30자를 넘을 수 없습니다."),
    TITLE_ALREADY_EXISTS(202, BAD_REQUEST.value(), "같은 제목의 게시글이 존재합니다."),
    TOO_MANY_REQUESTS(203, BAD_REQUEST.value(), "새로운 게시글 작성은 마지막 게시글 작성 이후 3분 뒤에 할 수 있습니다."),
    POST_NOT_FOUND(204, HttpStatus.NOT_FOUND.value(), "해당 ID의 게시글이 존재하지 않습니다."),

    //User
    EMAIL_ALREADY_EXISTS(300, BAD_REQUEST.value(), "이미 존재하는 이메일입니다.")
    ;

    private final int code;
    private final int httpStatus;
    private final String message;

    ErrorCode(int code, int httpStatus, String message) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }
}
