package org.sopt.service;

import org.sopt.common.exception.CustomException;
import org.sopt.domain.Post;
import org.sopt.domain.User;
import org.sopt.dto.response.PostResponse;
import org.sopt.repository.PostRepository;
import org.sopt.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import static org.sopt.common.exception.ErrorCode.*;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public Void createPost(String title, String content, Long userId) {
        if(postRepository.existsByTitle(title)) {
            throw new CustomException(TITLE_ALREADY_EXISTS);
        }
        validateCreatedAt();
        User user = getFindUser(userId);
        postRepository.save(new Post(title, content, user));
        return null;
    }

    private void validateCreatedAt() {
        LocalDateTime latestCreatedAt = postRepository.findLatestCreatedAt();
        if(latestCreatedAt == null) {
            return;
        }
        if(Duration.between(latestCreatedAt, LocalDateTime.now()).toMinutes() < 3) {
            throw new CustomException(TOO_MANY_REQUESTS);
        }
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

    private User getFindUser(Long userId){
        return userRepository.findById(userId).orElseThrow(() -> new CustomException((USER_NOT_FOUND)));
    }

    public List<PostResponse> getAllPostByKeyword(String keyword) {
        return postRepository.findAllByKeyword(keyword).stream().map(post -> new PostResponse(post.getId(), post.getTitle())).toList();
    }

}
