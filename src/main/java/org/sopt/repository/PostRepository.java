package org.sopt.repository;

import org.sopt.domain.Post;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostRepository {
    Map<Long, Post> postMap = new HashMap<>();

    public void save(Post post) {
        postMap.put(post.getId(), post);
    }

    public List<Post> findAll() {
        return new ArrayList<>(postMap.values());
    }

    public Post findById(Long id) {
        return postMap.get(id);
    }

    public boolean deleteById(Long id) {
        return postMap.remove(id) != null;
    }

    public boolean isExistByTitle(String title) {
        return postMap.values().stream().
                anyMatch(post -> post.getTitle().equals(title));
    }

    public List<Post> findAllByKeyword(String keyword) {
        return postMap.values().stream()
                .filter(post -> post.getTitle().contains(keyword))
                .toList();
    }
}
