package org.sopt.service;

import org.sopt.common.utils.IdGenrator;
import org.sopt.domain.Post;
import org.sopt.repository.PostRepository;

import java.util.List;

import static org.sopt.common.exception.ErrorMessage.*;

public class PostService {
    private final PostRepository postRepository = new PostRepository();

    public void createPost(String title) {
        validateTitle(title);
        Post post = new Post(IdGenrator.generateId(), title);
        postRepository.save(post);
    }

    private void validateTitle(String title) {
        if(title.isEmpty()){
            throw new IllegalArgumentException(EMPTY_TITLE.getMessage());
        }
        if(title.length() > 30){
            throw new IllegalArgumentException(INVALID_TITLE_LENGTH.getMessage());
        }
        if(postRepository.isExistByTitle(title)){
            throw new IllegalArgumentException(TITLE_ALREADY_EXISTS.getMessage());
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
        if(findPost == null){
            return false;
        }
        findPost.setTitle(newTitle);
        return true;
    }

}
