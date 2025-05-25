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

import static org.sopt.common.exception.ErrorCode.POST_NOT_FOUND;
import static org.sopt.common.exception.ErrorCode.USER_NOT_FOUND;

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
        Post post = postRepository.findById(postId).orElseThrow(() -> new CustomException(POST_NOT_FOUND));
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(USER_NOT_FOUND));

        Comment comment = Comment.builder()
                .content(content)
                .user(user)
                .post(post)
                .build();
        commentRepository.save(comment);

    }

}
