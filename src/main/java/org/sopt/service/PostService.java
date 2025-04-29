package org.sopt.service;

import org.sopt.common.exception.CustomException;
import org.sopt.common.utils.Validator;
import org.sopt.domain.Post;
import org.sopt.dto.response.PostResponse;
import org.sopt.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static org.sopt.common.exception.ErrorCode.POST_NOT_FOUND;
import static org.sopt.common.exception.ErrorCode.TITLE_ALREADY_EXISTS;

@Service
public class PostService {
    private final PostRepository postRepository;
    private LocalDateTime updatedAt;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void createPost(String title, String content) {
        if(postRepository.existsByTitle(title)) {
            throw new CustomException(TITLE_ALREADY_EXISTS);
        }
        Validator.validateUpdatedAt(updatedAt);
        postRepository.save(new Post(title, content));
        updatedAt = LocalDateTime.now();
    }

    public List<PostResponse> getAllPost() {
        return postRepository.findAll().stream().map(post -> new PostResponse(post.getId(), post.getTitle())).toList();
    }

    public PostResponse getPostDetailById(Long id) {
        Post findPost = getFindPost(id);
        return new PostResponse(findPost.getId(), findPost.getTitle());
    }

    public Void deletePostById(Long id) {
        Post findPost = getFindPost(id);
        postRepository.delete(findPost);
        return null;
    }

    public Void updatePost(Long updateId, String newTitle, String newContent) {
        if(postRepository.existsByTitle(newTitle)) {
            throw new CustomException(TITLE_ALREADY_EXISTS);
        }
        Post findPost = getFindPost(updateId);
        findPost.updateTitleAndContent(newTitle, newContent);
        postRepository.save(findPost);
        return null;
    }

    private Post getFindPost(Long updateId) {
        return postRepository.findById(updateId).orElseThrow(() -> new CustomException(POST_NOT_FOUND));
    }

    public List<PostResponse> getAllPostByKeyword(String keyword) {
        return postRepository.findAllByKeyword(keyword).stream().map(post -> new PostResponse(post.getId(), post.getTitle())).toList();
    }

}
