package org.sopt.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public record UserSignUpRequest(
        @NotBlank(message = "이름은 비어있을 수 없습니다.") @Length(max = 10, message = "이름은 10자를 넘을 수 없습니다.") String name,
        @NotBlank(message = "이메일은 비어있을 수 없습니다.")
        @Pattern(regexp="^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])+[.][a-zA-Z]{2,3}$", message = "이메일 주소 양식을 확인해주세요")
        String email) {
}
