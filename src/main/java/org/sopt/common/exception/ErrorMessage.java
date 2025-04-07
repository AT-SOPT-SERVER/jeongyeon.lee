package org.sopt.common.exception;

public enum ErrorMessage {
    EMPTY_TITLE("제목이 비어있습니다."),
    INVALID_TITLE_LENGTH("제목은 30자를 넘을 수 없습니다.")
    ;

    private String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
