package org.sopt.domain.post.dto.request;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import org.sopt.global.annotation.ValidTag;
import org.sopt.global.annotation.ValidTitle;

import java.util.List;

public record PostRequest(
        @NotBlank(message = "제목은 비어있을 수 없습니다.")  @ValidTitle String title,
        @NotBlank(message = "내용은 비어있을 수 없습니다.") @Length(max = 1000, message = "내용은 1000자를 넘을 수 없습니다.")  String content,
        @ValidTag List<String> tags) {
}
