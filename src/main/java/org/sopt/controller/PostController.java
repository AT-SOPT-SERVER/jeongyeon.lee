package org.sopt.controller;

import org.sopt.dto.request.PostRequest;
import org.sopt.dto.response.PostResponse;
import org.sopt.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Scanner;

@RestController
public class PostController {
    private final PostService postService;
    private final Scanner scanner = new Scanner(System.in);

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/posts")
    public void createPost(@RequestBody final PostRequest req){
        postService.createPost(req.title());
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PostResponse>> getAllPosts(){
        return ResponseEntity.ok(postService.getAllPost());
    }



    public void getPostDetailById(){
        System.out.println("\n🔍 [게시글 상세 조회]");
        System.out.print("📌 조회할 게시글 ID를 입력해주세요: ");
        Long id = Long.parseLong(scanner.nextLine());
        postService.getPostDetailById(id);
    }

    public void deletePostById(){
        System.out.println("\n🗑️ [게시글 삭제]");
        System.out.print("📌 삭제할 게시글 ID를 입력해주세요: ");
        Long deleteId = Long.parseLong(scanner.nextLine());
        postService.deletePostById(deleteId);
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
