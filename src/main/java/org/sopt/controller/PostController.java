package org.sopt.controller;

import org.sopt.domain.Post;
import org.sopt.service.PostService;

import java.util.List;

public class PostController {
    private PostService postService = new PostService();
    private int postId;


    public void createPost(String title){
        Post post = new Post(postId++, title);
        postService.createPost(post);
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
