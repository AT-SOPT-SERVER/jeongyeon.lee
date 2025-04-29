package org.sopt.dto.request;

import jakarta.validation.constraints.NotBlank;

public record PostUpdateRequest(Long updateId,
                                @NotBlank(message = "제목은 비어있을 수 없습니다.") String newTitle,
                               @NotBlank(message = "내용은 비어있을 수 없습니다.") String newContent) {
}
