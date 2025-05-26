package org.sopt.domain.post.repository;

import org.sopt.domain.post.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;


@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    boolean existsByTitle(String title);

    @Query("select p.createdAt from Post p order by p.createdAt desc limit 1")
    LocalDateTime findLatestCreatedAt();

    Page<Post> findAllByOrderByCreatedAtDesc(Pageable pageable);

    }
