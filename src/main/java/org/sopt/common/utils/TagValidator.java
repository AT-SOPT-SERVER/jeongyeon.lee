package org.sopt.common.utils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.sopt.common.annotation.ValidTag;

import java.util.Set;

public class TagValidator implements ConstraintValidator<ValidTag, String> {

    private static final Set<String> ALLOWED_TAGS = Set.of("백엔드", "데이터베이스", "인프라");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && ALLOWED_TAGS.contains(value);
    }
}
