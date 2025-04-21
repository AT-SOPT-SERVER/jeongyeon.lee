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

    public void updatePostTitle(){
        System.out.println("\n✏️ [게시글 수정]");
        System.out.print("📌 수정할 게시글 ID를 입력해주세요: ");
        Long updateId = Long.parseLong(scanner.nextLine());
        System.out.print("📝 새 제목을 입력해주세요: ");
        String newTitle = scanner.nextLine();
        postService.updatePost(updateId, newTitle);
    }

    public void searchPostsByKeyword(){
        System.out.println("\n🔎 [게시글 검색]");
        System.out.print("검색할 키워드를 입력해주세요: ");
        String keyword = scanner.nextLine();
        postService.getAllPostByKeyword(keyword);
    }

}
