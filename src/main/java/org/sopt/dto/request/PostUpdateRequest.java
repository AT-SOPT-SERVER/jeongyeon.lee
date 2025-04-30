package org.sopt.dto.request;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record PostUpdateRequest(Long updateId,
                                @NotBlank(message = "제목은 비어있을 수 없습니다.")  @Length(max = 30, message = "제목은 30자를 넘을 수 없습니다.") String newTitle,
                                @NotBlank(message = "내용은 비어있을 수 없습니다.") @Length(max = 1000, message = "내용은 1000자를 넘을 수 없습니다.")  String newContent) {
}
