package org.sopt.dto.request;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import org.sopt.common.annotation.ValidTag;
import org.sopt.common.annotation.ValidTitle;

public record PostRequest(
        @NotBlank(message = "제목은 비어있을 수 없습니다.")  @ValidTitle String title,
        @NotBlank(message = "내용은 비어있을 수 없습니다.") @Length(max = 1000, message = "내용은 1000자를 넘을 수 없습니다.")  String content,
        @NotBlank(message = "태그는 비어있을 수 없습니다.") @ValidTag String tag) {
}
