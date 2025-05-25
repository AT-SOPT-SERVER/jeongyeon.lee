package org.sopt.dto.request;

import org.sopt.common.annotation.ValidComment;

public record CommentUpdateRequest(Long commentId, @ValidComment String newContent) {
}
