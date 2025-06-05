package org.sopt.global.utils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.sopt.global.annotation.ValidTitle;

public class TitleValidator implements ConstraintValidator<ValidTitle, String> {
    private static final int MAX_LENGTH = 30;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return TextUtils.getLengthOfEmojiContainableText(value) <= MAX_LENGTH;
    }
}
