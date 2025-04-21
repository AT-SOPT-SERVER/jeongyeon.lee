package org.sopt.controller;

import org.sopt.dto.request.PostRequest;
import org.sopt.dto.response.PostResponse;
import org.sopt.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Scanner;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;
    private final Scanner scanner = new Scanner(System.in);

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping()
    public void createPost(@RequestBody final PostRequest req){
        postService.createPost(req.title());
    }

    @GetMapping()
    public ResponseEntity<List<PostResponse>> getAllPosts(){
        return ResponseEntity.ok(postService.getAllPost());
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostResponse> getPostDetailById(@PathVariable final Long postId){
        return ResponseEntity.ok(postService.getPostDetailById(postId));
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePostById(@PathVariable final Long postId){
       return ResponseEntity.ok(postService.deletePostById(postId));
    }

    @PutMapping()
    public ResponseEntity<Void> updatePostTitle(@RequestBody final PostRequest req){
        return ResponseEntity.ok(postService.updatePost(req.updateId(), req.title()));
    }

    public void searchPostsByKeyword(){
        System.out.println("\n🔎 [게시글 검색]");
        System.out.print("검색할 키워드를 입력해주세요: ");
        String keyword = scanner.nextLine();
        postService.getAllPostByKeyword(keyword);
    }

}
