package org.sopt.controller;

import org.sopt.domain.Post;
import org.sopt.service.PostService;

import java.util.List;

public class PostController {
    private final PostService postService = new PostService();

    public void createPost(String title){
        postService.createPost(title);
    }

    public List<Post> getAllPosts(){
        return postService.getAllPost();
    }

    public void getPostById(Long id){
        postService.getPostById(id);
    }

    public void deletePostById(Long id){
        postService.deletePostById(id);
    }

    public void updatePostTitle(Long id, String title){
        postService.updatePost(id, title);
    }

    public void searchPostsByKeyword(String keyword){
        postService.getAllPostByKeyword(keyword);
    }

    public void savePostsToFile(){
        postService.savePostsToFile();
    }

    public void loadPostsFromFile(){
        postService.loadPostsFromFile();
    }
}
