package org.sopt.domain.comment.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sopt.global.response.BaseResponse;
import org.sopt.domain.comment.dto.request.CommentRequest;
import org.sopt.domain.comment.dto.request.CommentUpdateRequest;
import org.sopt.domain.comment.service.CommentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    @PostMapping()
    public BaseResponse<Void> addComment(@RequestHeader Long userId, @RequestBody @Valid CommentRequest req){
        commentService.createComment(userId, req.postId(), req.content());
        return BaseResponse.ok(null);
    }

    @PutMapping()
    public BaseResponse<Void> updateComment(@RequestHeader Long userId, @RequestBody @Valid CommentUpdateRequest req){
        commentService.updateComment(userId, req.commentId(), req.newContent());
        return BaseResponse.ok(null);
    }

    @DeleteMapping("/{commentId}")
    public BaseResponse<Void> deleteComment(@RequestHeader Long userId, @PathVariable Long commentId){
        commentService.deleteComment(userId, commentId);
        return BaseResponse.ok(null);
    }
}
