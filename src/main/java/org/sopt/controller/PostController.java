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

    public void getPostById(int id){
        postService.getPostById(id);
    }

    public boolean deletePostById(int id){
        return postService.deletePostById(id);
    }

    public boolean updatePostTitle(int id, String title){
        return postService.updatePost(id, title);
    }

    public List<Post> searchPostsByKeyword(String keyword){
        return postService.getAllPostByKeyword(keyword);
    }

    public void savePostsToFile() throws Exception {
        postService.savePostsToFile();
    }

    public void loadPostsFromFile() throws Exception {
        postService.loadPostsFromFile();
    }
}
