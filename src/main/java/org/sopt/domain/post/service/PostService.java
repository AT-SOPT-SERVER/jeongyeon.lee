package org.sopt.domain.post.service;

import lombok.RequiredArgsConstructor;
import org.sopt.domain.comment.repository.CommentRepository;
import org.sopt.domain.post.dto.response.PostDetailResponse;
import org.sopt.domain.post.dto.response.PostResponse;
import org.sopt.domain.post.model.Post;
import org.sopt.domain.post.model.PostLike;
import org.sopt.domain.post.repository.PostLikeRespository;
import org.sopt.domain.post.repository.PostRepository;
import org.sopt.domain.user.model.User;
import org.sopt.domain.user.repository.UserRepository;
import org.sopt.global.exception.CustomException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static org.sopt.global.exception.ErrorCode.*;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final PostLikeRespository postLikeRespository;

    @Transactional
    public Void createPost(String title, String content, String tag, Long userId) {
        if(postRepository.existsByTitle(title)) {
            throw new CustomException(TITLE_ALREADY_EXISTS);
        }
        validateCreatedAt();
        User user = getFindUser(userId);
        Post post = new Post(title, content, tag);
        user.addPost(post);
        postRepository.save(post);

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
        return postRepository.findAllByOrderByCreatedAt().stream().map(post -> new PostResponse(
                post.getTitle(),
                post.getUser().getName())).toList();
    }

    public PostDetailResponse getPostDetailById(Long id) {
        Post findPost = getFindPost(id);

        return new PostDetailResponse(findPost.getTitle(),
                findPost.getContent(),
                findPost.getUser().getName(),
                commentRepository.findAllContentByPostId(findPost.getId()));
    }

    @Transactional
    public Void deletePostById(Long id, Long userId) {
        Post findPost = getFindPost(id);
        if(!Objects.equals(findPost.getUser().getId(), userId)) {
            throw new CustomException(CANNOT_DELETE_POST);
        }
        postRepository.delete(findPost);
        return null;
    }

    @Transactional
    public Void updatePost(Long userId, Long updateId, String newTitle, String newContent) {
        Post findPost = getFindPost(updateId);
        validateUpdatePost(userId, newTitle, findPost);
        findPost.updateTitleAndContent(newTitle, newContent);
        postRepository.save(findPost);
        return null;
    }

    private void validateUpdatePost(Long userId, String newTitle, Post findPost) {
        if(postRepository.existsByTitle(newTitle)) {
            throw new CustomException(TITLE_ALREADY_EXISTS);
        }
        if(!Objects.equals(findPost.getUser().getId(), userId)) {
            throw new CustomException(CANNOT_UPDATE_POST);
        }
    }

    private Post getFindPost(Long updateId) {
        return postRepository.findById(updateId).orElseThrow(() -> new CustomException(POST_NOT_FOUND));
    }

    private User getFindUser(Long userId){
        return userRepository.findById(userId).orElseThrow(() -> new CustomException((USER_NOT_FOUND)));
    }

    public List<PostDetailResponse> getAllPostByTitle(String keyword) {
        return postRepository.findAllByTitle(keyword).stream().map(post -> new PostDetailResponse(post.getTitle(),
                post.getContent(),
                post.getUser().getName(),
                        commentRepository.findAllContentByPostId(post.getId()))).
                toList();
    }

    public List<PostDetailResponse> getAllPostByUserName(String userName){
        return postRepository.findAllByUserName(userName).stream().map(post -> new PostDetailResponse(post.getTitle(),
                        post.getContent(),
                        post.getUser().getName(),
                commentRepository.findAllContentByPostId(post.getId()))).
                toList();
    }

    public List<PostDetailResponse> getAllPostByTag(String tag){
        return postRepository.findAllByTag(tag).stream().map(post -> new PostDetailResponse(post.getTitle(),
                        post.getContent(),
                        post.getUser().getName(),
                commentRepository.findAllContentByPostId(post.getId()))).
                toList();
    }

    @Transactional
    public void addLike(Long userId, Long postId){
        Post findPost = getFindPost(postId);
        User findUser = getFindUser(userId);
        if(postLikeRespository.existsByPostAndUser(findPost, findUser)){
            findPost.decreaseLikeCount();
            postLikeRespository.deleteByUserAndPost(findUser, findPost);
            return;
        }
        findPost.increaseLikeCount();
        postLikeRespository.save(new PostLike(findPost, findUser));


    }

}
