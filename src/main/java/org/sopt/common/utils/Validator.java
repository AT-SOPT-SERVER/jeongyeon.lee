package org.sopt.common.utils;

import org.sopt.common.exception.CustomException;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.sopt.common.exception.ErrorCode.TOO_MANY_REQUESTS;

public class Validator {

    public static void validateUpdatedAt(LocalDateTime updatedAt) {
        if (updatedAt != null && Duration.between(updatedAt, LocalDateTime.now()).toMinutes() < 3) {
            throw new CustomException(TOO_MANY_REQUESTS);
        }
    }
}