package org.sopt.global.utils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.sopt.global.annotation.ValidTag;

import java.util.List;
import java.util.Set;

public class TagValidator implements ConstraintValidator<ValidTag, List<String>> {

    private static final Set<String> ALLOWED_TAGS = Set.of("백엔드", "데이터베이스", "인프라", "기타");

    @Override
    public boolean isValid(List<String> tags, ConstraintValidatorContext context) {
        if (tags == null || tags.size() > 2) return false;
        return ALLOWED_TAGS.containsAll(tags);
    }
}
