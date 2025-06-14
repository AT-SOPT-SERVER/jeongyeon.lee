package org.sopt.domain.post.dto.response;

import java.util.List;

public record PostDetailResponse(String title, String content, String userName,
                                 List<String> comments) {
}
