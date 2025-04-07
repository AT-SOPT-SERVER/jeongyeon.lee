package org.sopt.common.exception;

public enum ErrorMessage {
    EMPTY_TITLE("제목이 비어있습니다."),
    INVALID_TITLE_LENGTH("제목은 30자를 넘을 수 없습니다."),
    TITLE_ALREADY_EXISTS("같은 제목의 게시글이 존재합니다.")
    ;

    private String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
