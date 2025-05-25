package org.sopt.common.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.sopt.common.utils.CommentValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CommentValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidComment {
    String message() default "댓글은 최대 300자까지 입력할 수 있습니다 (이모지 포함)";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
