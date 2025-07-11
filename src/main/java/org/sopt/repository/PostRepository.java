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

    @Query("select p from Post p join fetch p.user where p.title like concat('%', :keyword, '%')")
    List<Post> findAllByTitle(@Param("keyword") String keyword);

    @Query("select p.createdAt from Post p order by p.createdAt desc limit 1")
    LocalDateTime findLatestCreatedAt();

    @Query("select p from Post p join fetch p.user order by p.createdAt desc")
    List<Post> findAllByOrderByCreatedAt();

    @Query("select p from Post p join fetch p.user where p.user.name like concat('%', :userName, '%')")
    List<Post> findAllByUserName(@Param("userName") String userName);

    @Query("select p from Post p join fetch p.user where p.tag = :tag")
    List<Post> findAllByTag(@Param("tag") String tag);
}
