package org.sopt.service;

import org.sopt.common.exception.ErrorMessage;
import org.sopt.domain.Post;
import org.sopt.repository.PostRepository;

import java.util.List;

import static org.sopt.common.exception.ErrorMessage.*;

public class PostService {
    private PostRepository postRepository = new PostRepository();
    private int postId;

    public void createPost(String title) {
        validateTitle(title);
        Post post = new Post(postId++, title);
        postRepository.save(post);
    }

    private void validateTitle(String title) {
        if(title.isEmpty()){
            throw new IllegalArgumentException(EMPTY_TITLE.getMessage());
        }
        if(title.length() > 30){
            throw new IllegalArgumentException(INVALID_TITLE_LENGTH.getMessage());
        }
    }

    public List<Post> getAllPost(){
        return postRepository.findAll();
    }

    public Post getPostById(int id) {
        return postRepository.findById(id);
    }

    public boolean deletePostById(int id) {
        return postRepository.deleteById(id);
    }

    public boolean updatePost(int updateId, String newTitle){
        Post findPost = postRepository.findById(updateId);
        if(findPost.equals(null)){
            return false;
        }
        findPost.setTitle(newTitle);
        return true;
    }

}
