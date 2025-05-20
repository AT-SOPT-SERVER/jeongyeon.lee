package org.sopt.common.utils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.sopt.common.annotation.ValidTitle;

public class TitleValidator implements ConstraintValidator<ValidTitle, String> {
    private static final int MAX_LENGTH = 30;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return true;
        return TextUtils.getLengthOfEmojiContainableText(value) <= MAX_LENGTH;
    }
}
