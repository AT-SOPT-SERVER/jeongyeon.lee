package org.sopt.domain.post.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AllArgsConstructor;
import org.sopt.domain.post.model.Post;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.sopt.domain.post.model.QPost.post;
import static org.sopt.domain.post.model.QPostTag.postTag;

@Repository
@AllArgsConstructor
public class PostCustomRepositoryImpl implements PostCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Post> searchByTitle(String title) {
        return queryFactory.selectFrom(post)
                .where(post.title.contains(title))
                .fetch();
    }

    @Override
    public List<Post> searchByAuthor(String author) {
        return queryFactory.selectFrom(post)
                .where(post.user.name.contains(author))
                .fetch();
    }

    @Override
    public List<Post> searchByTag(String tag) {
        return queryFactory
                .selectFrom(post)
                .distinct()
                .join(post.tags,postTag).fetchJoin()
                .where(postTag.tagName.eq(tag))
                .fetch();

    }

}
