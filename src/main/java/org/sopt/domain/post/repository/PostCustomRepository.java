package org.sopt.domain.post.repository;

import org.sopt.domain.post.model.Post;

import java.util.List;

public interface PostCustomRepository {
    List<Post> searchByTitle(String title);
    List<Post> searchByAuthor(String author);
    List<Post> searchByTag(String tag);
}
