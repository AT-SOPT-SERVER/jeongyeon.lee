package org.sopt.domain.comment.repository;

import org.sopt.domain.comment.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("select c.content from Comment c where c.post.id = :postId")
    List<String> findAllContentByPostId(Long postId);
}
