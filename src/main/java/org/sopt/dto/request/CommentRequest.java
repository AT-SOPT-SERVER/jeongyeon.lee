package org.sopt.dto.request;

import org.sopt.common.annotation.ValidComment;

public record CommentRequest(Long postId, @ValidComment String content) {
}
