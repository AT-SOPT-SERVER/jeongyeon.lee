package org.sopt.domain.post.repository;

import org.sopt.domain.post.model.Post;
import org.sopt.domain.post.model.PostLike;
import org.sopt.domain.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostLikeRespository extends JpaRepository<PostLike, Long> {

    boolean existsByPostAndUser(Post post, User user);

    void deleteByUserAndPost(User user, Post post);
}
