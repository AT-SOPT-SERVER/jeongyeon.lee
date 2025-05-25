package org.sopt.dto.response;

import java.util.List;

public record PostDetailResponse(String title, String content, String userName,
                                 List<String> comments) {
}
