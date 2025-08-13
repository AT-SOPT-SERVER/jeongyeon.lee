package org.sopt.domain.post.dto.response;

import java.io.Serializable;

public record PostResponse(String title, String userName) implements Serializable {
}
