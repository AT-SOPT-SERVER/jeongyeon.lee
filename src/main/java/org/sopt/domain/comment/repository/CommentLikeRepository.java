package org.sopt.domain.comment.repository;

import org.sopt.domain.comment.model.Comment;
import org.sopt.domain.comment.model.CommentLike;
import org.sopt.domain.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {
    boolean existsByCommentAndUser(Comment comment, User user);

    void deleteByUserAndComment(User user, Comment comment);
}
