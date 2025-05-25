package org.sopt.domain.post.controller;

import jakarta.validation.Valid;
import org.sopt.global.response.BaseResponse;
import org.sopt.domain.post.dto.request.PostRequest;
import org.sopt.domain.post.dto.request.PostUpdateRequest;
import org.sopt.domain.post.dto.response.PostDetailResponse;
import org.sopt.domain.post.dto.response.PostResponse;
import org.sopt.domain.post.service.PostService;
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
        return BaseResponse.ok(postService.createPost(req.title(), req.content(), req.tag(), userId));
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

    @GetMapping("/search-tag")
    public BaseResponse<List<PostDetailResponse>> searchPostsByTag(@RequestParam final String tag) {
        return BaseResponse.ok(postService.getAllPostByTag(tag));
    }

    @PostMapping("like/{postId}")
    public BaseResponse<Void> setPostLike(@RequestHeader Long userId, @PathVariable final Long postId) {
        postService.addLike(userId, postId);
        return BaseResponse.ok(null);
    }

}
