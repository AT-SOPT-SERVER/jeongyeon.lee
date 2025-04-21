package org.sopt.service;

import org.sopt.common.utils.Validator;
import org.sopt.domain.Post;
import org.sopt.dto.response.PostResponse;
import org.sopt.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static org.sopt.common.exception.ErrorMessage.*;

@Service
public class PostService {
    private final PostRepository postRepository;
    private LocalDateTime updatedAt;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void createPost(String title) {
        Validator.validateTitle(title, postRepository);
        Validator.validateUpdatedAt(updatedAt);
        postRepository.save(new Post(title));
        updatedAt = LocalDateTime.now();
    }

    public List<PostResponse> getAllPost(){
        return postRepository.findAll().stream().map(post -> new PostResponse(post.getId(), post.getTitle())).toList();
    }

    public void getPostDetailById(Long id) {

    }

    public void deletePostById(Long id) {

    }

    public void updatePost(Long updateId, String newTitle){

    }

    private Post getFindPost(Long updateId) {
        return postRepository.findById(updateId).orElseThrow(() -> new IllegalArgumentException(POST_NOT_FOUND.getMessage()));
    }

    public void getAllPostByKeyword(String keyword){

    }

}
