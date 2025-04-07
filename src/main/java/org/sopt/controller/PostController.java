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

    public Post getPostById(int id){
        return postService.getPostById(id);
    }

    public boolean deletePostById(int id){
        return postService.deletePostById(id);
    }

    public boolean updatePostTitle(int id, String title){
        return postService.updatePost(id, title);
    }
}
