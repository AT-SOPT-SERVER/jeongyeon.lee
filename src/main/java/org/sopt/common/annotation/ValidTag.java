package org.sopt.common.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.sopt.common.utils.TagValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = TagValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidTag {
    String message() default "허용되지 않은 태그입니다. (백엔드, 데이터베이스, 인프라 중 하나여야 함)";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
