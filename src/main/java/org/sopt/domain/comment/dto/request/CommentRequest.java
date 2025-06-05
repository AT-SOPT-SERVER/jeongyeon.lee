package org.sopt.domain.comment.dto.request;

import jakarta.validation.constraints.NotBlank;
import org.sopt.global.annotation.ValidComment;

public record CommentRequest(Long postId,
                             @ValidComment @NotBlank(message = "댓글 내용은 비어있을 수 없습니다.") String content) {
}
