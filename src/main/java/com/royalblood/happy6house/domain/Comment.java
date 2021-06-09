package com.royalblood.happy6house.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id",
                nullable = false,
                foreignKey = @ForeignKey(name = "FK_COMMENT_USER"))
    private User user;

    @JsonIgnore
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "post_id",
                nullable = false,
                foreignKey = @ForeignKey(name = "FK_COMMENT_POST"))
    private Post post;

    @ManyToOne
    @JoinColumn(name = "parent_id",
                foreignKey = @ForeignKey(name = "FK_COMMENT_PARENT"))
    private Comment parent;

    @Column(nullable = false)
    private boolean deleted = false;

    @Builder
    public Comment(String content, User user, Post post, Comment parent) {
        this.content = content;
        this.user = user;
        this.post = post;
        this.parent = parent;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void update(String content) {
        this.content = content;
    }

}
