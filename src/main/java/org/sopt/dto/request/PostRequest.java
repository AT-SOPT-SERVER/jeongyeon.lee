package org.sopt.dto.request;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import org.sopt.common.exception.CustomException;
import org.sopt.common.utils.TextUtils;

import static org.sopt.common.exception.ErrorCode.INVALID_TITLE_LENGTH;

public record PostRequest(
        @NotBlank(message = "제목은 비어있을 수 없습니다.") String title,
        @NotBlank(message = "내용은 비어있을 수 없습니다.") @Length(max = 1000, message = "내용은 1000자를 넘을 수 없습니다.")  String content) {
    public PostRequest {
        if (TextUtils.getLengthOfEmojiContainableText(title) > 30) {
            throw new CustomException(INVALID_TITLE_LENGTH);
        }
    }
}
