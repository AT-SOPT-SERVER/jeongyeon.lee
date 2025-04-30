package org.sopt.repository;

import org.sopt.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    boolean existsByTitle(String title);

    @Query("select p from Post p where p.title like concat('%', :keyword, '%')")
    List<Post> findAllByKeyword(@Param("keyword") String keyword);

    @Query("select p.createdAt from Post p order by p.createdAt limit 1")
    LocalDateTime findLatestCreatedAt();
}
