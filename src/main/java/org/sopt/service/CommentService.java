package org.sopt.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sopt.common.exception.CustomException;
import org.sopt.domain.Comment;
import org.sopt.domain.Post;
import org.sopt.domain.User;
import org.sopt.repository.CommentRepository;
import org.sopt.repository.PostRepository;
import org.sopt.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.sopt.common.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public void createComment(Long userId, Long postId, String content){
        Post post = getPost(postId);
        User user = getUser(userId);

        Comment comment = Comment.builder()
                .content(content)
                .user(user)
                .post(post)
                .build();
        commentRepository.save(comment);

    }

    private User getUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new CustomException(USER_NOT_FOUND));
    }

    private Post getPost(Long postId) {
        return postRepository.findById(postId).orElseThrow(() -> new CustomException(POST_NOT_FOUND));
    }

    private Comment getComment(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(() -> new CustomException(COMMENT_NOT_FOUND));
    }

    @Transactional
    public void updateComment(Long userId, Long commentId, String newContent){
        User user = getUser(userId);
        Comment comment = getComment(commentId);
        comment.updateContent(newContent);
        if(!user.equals(comment.getUser())){
            throw new CustomException(CANNOT_UPDATE_COMMENT);
        }
        commentRepository.save(comment);
    }

}
