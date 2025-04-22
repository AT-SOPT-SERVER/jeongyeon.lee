package org.sopt.common.utils;

import org.sopt.common.exception.CustomException;
import org.sopt.repository.PostRepository;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.sopt.common.exception.ErrorCode.*;

public class Validator {

    public static void validateUpdatedAt(LocalDateTime updatedAt) {
        if (updatedAt != null && Duration.between(updatedAt, LocalDateTime.now()).toMinutes() < 3) {
            throw new CustomException(TOO_MANY_REQUESTS);
        }
    }

    public static void validateTitle(String title, PostRepository postRepository) {
        if (title.isEmpty()) {
            throw new CustomException(EMPTY_TITLE);
        }
        if (TextUtils.getLengthOfEmojiContainableText(title) > 30) {
            throw new CustomException(INVALID_TITLE_LENGTH);
        }
        if (postRepository.existsByTitle(title)) {
            throw new CustomException(TITLE_ALREADY_EXISTS);
        }
    }
}