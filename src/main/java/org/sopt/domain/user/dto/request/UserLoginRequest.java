package org.sopt.domain.user.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UserLoginRequest(@NotBlank(message = "이메일은 비어있을 수 없습니다.") String email) {
}
