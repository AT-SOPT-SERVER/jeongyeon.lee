package org.sopt.common.utils;

import org.sopt.common.utils.TextUtils;
import org.sopt.repository.PostRepository;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.sopt.common.exception.ErrorMessage.*;

public class Validator {

    public static void validateUpdatedAt(LocalDateTime updatedAt) {
        if(updatedAt != null && Duration.between(updatedAt, LocalDateTime.now()).toMinutes() < 3){
            throw new IllegalStateException(TOO_MANY_REQUESTS.getMessage());
        }
    }

    public static void validateTitle(String title, PostRepository postRepository) {
        if(title.isEmpty()){
            throw new IllegalArgumentException(EMPTY_TITLE.getMessage());
        }
        if(TextUtils.getLengthOfEmojiContainableText(title) > 30){
            throw new IllegalArgumentException(INVALID_TITLE_LENGTH.getMessage());
        }
        if(postRepository.isExistByTitle(title)){
            throw new IllegalArgumentException(TITLE_ALREADY_EXISTS.getMessage());
        }
    }
}