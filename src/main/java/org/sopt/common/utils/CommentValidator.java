package org.sopt.common.utils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.sopt.common.annotation.ValidComment;

public class CommentValidator implements ConstraintValidator<ValidComment, String> {
    private static final int MAX_LENGTH = 300;
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return TextUtils.getLengthOfEmojiContainableText(s) <= MAX_LENGTH;
    }
}
