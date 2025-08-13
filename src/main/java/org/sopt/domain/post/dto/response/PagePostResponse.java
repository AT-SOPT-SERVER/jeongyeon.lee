package org.sopt.domain.post.dto.response;

import java.io.Serializable;
import java.util.List;

public record PagePostResponse(List<PostResponse> posts, int currentPage, int totalPage) implements Serializable {
}
