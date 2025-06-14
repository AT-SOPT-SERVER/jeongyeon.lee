package org.sopt.domain.comment.dto.request;

import org.sopt.global.annotation.ValidComment;

public record CommentUpdateRequest(Long commentId, @ValidComment String newContent) {
}
