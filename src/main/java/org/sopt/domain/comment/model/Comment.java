package org.sopt.domain.comment.model;

import jakarta.persistence.*;
import lombok.*;
import org.sopt.domain.post.model.Post;
import org.sopt.domain.user.model.User;

@Entity
@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public void updateContent(String newContent){
        this.content = newContent;
    }
}
