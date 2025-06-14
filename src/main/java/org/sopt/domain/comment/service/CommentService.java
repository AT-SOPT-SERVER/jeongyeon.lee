package org.sopt.domain.comment.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sopt.domain.comment.model.Comment;
import org.sopt.domain.comment.model.CommentLike;
import org.sopt.domain.comment.repository.CommentLikeRepository;
import org.sopt.domain.comment.repository.CommentRepository;
import org.sopt.domain.post.model.Post;
import org.sopt.domain.post.repository.PostRepository;
import org.sopt.domain.user.model.User;
import org.sopt.domain.user.repository.UserRepository;
import org.sopt.global.exception.CustomException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.sopt.global.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentLikeRepository commentLikeRepository;

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

    @Transactional
    public void deleteComment(Long userId, Long commentId){
        User user = getUser(userId);
        Comment comment = getComment(commentId);
        if(!user.equals(comment.getUser())){
            throw new CustomException(CANNOT_DELETE_COMMENT);
        }
        commentRepository.delete(comment);
    }

    @Transactional
    public void addLike(Long userId, Long commentId){
        Comment comment = getComment(commentId);
        User user = getUser(userId);
        if(commentLikeRepository.existsByCommentAndUser(comment, user)){
            comment.decreaseLikeCount();
            commentLikeRepository.deleteByUserAndComment(user, comment);
            return;
        }
        comment.increaseLikeCount();
        commentLikeRepository.save(new CommentLike(comment, user));

    }

}
