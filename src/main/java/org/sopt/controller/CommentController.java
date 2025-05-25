package org.sopt.controller;

import lombok.RequiredArgsConstructor;
import org.sopt.common.response.BaseResponse;
import org.sopt.dto.request.CommentRequest;
import org.sopt.service.CommentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    @PostMapping()
    public BaseResponse<Void> addComment(@RequestHeader Long userId, @RequestBody CommentRequest req){
        commentService.createComment(userId, req.postId(), req.content());
        return BaseResponse.ok(null);
    }
}
