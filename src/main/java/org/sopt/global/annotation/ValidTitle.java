package org.sopt.global.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.sopt.global.utils.TitleValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = TitleValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidTitle {
    String message() default "제목은 최대 30자까지 입력할 수 있습니다 (이모지 포함)";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
