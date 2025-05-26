package org.sopt.global.exception;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

public enum ErrorCode {
    //common
    METHOD_NOT_ALLOWED(100, HttpStatus.METHOD_NOT_ALLOWED.value(), "유효하지 않은 Http 메서드입니다."),
    API_NOT_FOUND(101, HttpStatus.NOT_FOUND.value(), "존재하지 않는 API 입니다."),
    INTERNAL_SERVER_ERROR(102, HttpStatus.INTERNAL_SERVER_ERROR.value(), "서버 내부 오류입니다."),

    //Post
    INVALID_TITLE_LENGTH(201, BAD_REQUEST.value(), "제목은 30자를 넘을 수 없습니다."),
    TITLE_ALREADY_EXISTS(202, CONFLICT.value(), "같은 제목의 게시글이 존재합니다."),
    TOO_MANY_REQUESTS(203, BAD_REQUEST.value(), "새로운 게시글 작성은 마지막 게시글 작성 이후 3분 뒤에 할 수 있습니다."),
    POST_NOT_FOUND(204, HttpStatus.NOT_FOUND.value(), "해당 ID의 게시글이 존재하지 않습니다."),
    CANNOT_UPDATE_POST(205, FORBIDDEN.value(), "게시물 작성자 본인만 게시물을 수정할 수 있습니다."),
    CANNOT_DELETE_POST(206, FORBIDDEN.value(), "게시물 작성자 본인만 게시물을 삭제할 수 있습니다."),
    BAD_KEYWORD(207, BAD_REQUEST.value(), "잘못된 검색 기준입니다."),
    INVALID_TAG(208, BAD_REQUEST.value(), "태그는 최대 2개까지만 가능합니다."),

    //User
    EMAIL_ALREADY_EXISTS(300, CONFLICT.value(), "이미 존재하는 이메일입니다."),
    USER_NOT_FOUND(301, NOT_FOUND.value(), "존재하지 않는 사용자입니다."),

    //Comment
    COMMENT_NOT_FOUND(400, NOT_FOUND.value(), "존재하지 않는 댓글입니다."),
    CANNOT_UPDATE_COMMENT(401, FORBIDDEN.value(), "댓글 작성자 본인만 댓글을 수정할 수 있습니다."),
    CANNOT_DELETE_COMMENT(402, FORBIDDEN.value(), "댓글 작성자 본인만 댓글을 삭제할 수 있습니다.")
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
