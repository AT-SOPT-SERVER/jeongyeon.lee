package org.sopt.controller;

import jakarta.validation.Valid;
import org.sopt.common.response.BaseResponse;
import org.sopt.dto.request.PostRequest;
import org.sopt.dto.request.PostUpdateRequest;
import org.sopt.dto.response.PostDetailResponse;
import org.sopt.dto.response.PostResponse;
import org.sopt.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping()
    public BaseResponse<Void> createPost(@RequestBody  @Valid final PostRequest req,
                                         @RequestHeader final Long userId) {
        return BaseResponse.ok(postService.createPost(req.title(), req.content(), userId));
    }

    @GetMapping()
    public BaseResponse<List<PostResponse>> getAllPosts() {
        return BaseResponse.ok(postService.getAllPost());
    }

    @GetMapping("/{postId}")
    public BaseResponse<PostDetailResponse> getPostDetailById(@PathVariable final Long postId) {
        return BaseResponse.ok(postService.getPostDetailById(postId));
    }

    @DeleteMapping("/{postId}")
    public BaseResponse<Void> deletePostById(@PathVariable final Long postId, @RequestHeader final Long userId) {
        return BaseResponse.ok(postService.deletePostById(postId, userId));
    }

    @PutMapping()
    public BaseResponse<Void> updatePostTitle(@RequestBody @Valid final PostUpdateRequest req,
                                              @RequestHeader final Long userId) {
        return BaseResponse.ok(postService.updatePost(userId, req.updateId(), req.newTitle(), req.newContent()));
    }

    @GetMapping("/search-title")
    public BaseResponse<List<PostDetailResponse>> searchPostsByTitle(@RequestParam final String keyword) {
        return BaseResponse.ok(postService.getAllPostByTitle(keyword));
    }

    @GetMapping("/search-author")
    public BaseResponse<List<PostDetailResponse>> searchPostsByAuthor(@RequestParam final String userName) {
        return BaseResponse.ok(postService.getAllPostByUserName(userName));
    }

}
