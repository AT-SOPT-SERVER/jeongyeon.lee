package org.sopt.domain;

import java.time.LocalDateTime;

public class Post {
    private final int id;
    private String title;

    public Post(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
