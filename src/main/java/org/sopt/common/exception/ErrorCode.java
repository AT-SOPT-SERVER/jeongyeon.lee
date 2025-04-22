package org.sopt.common.exception;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

public enum ErrorCode {
    //Post
    EMPTY_TITLE(200, BAD_REQUEST.value(), "제목이 비어있습니다."),
    INVALID_TITLE_LENGTH(201, BAD_REQUEST.value(), "제목은 30자를 넘을 수 없습니다."),
    TITLE_ALREADY_EXISTS(202, BAD_REQUEST.value(), "같은 제목의 게시글이 존재합니다."),
    TOO_MANY_REQUESTS(203, BAD_REQUEST.value(), "새로운 게시글 작성은 마지막 게시글 작성 이후 3분 뒤에 할 수 있습니다."),
    POST_NOT_FOUND(204, HttpStatus.NOT_FOUND.value(), "해당 ID의 게시글이 존재하지 않습니다."),
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
