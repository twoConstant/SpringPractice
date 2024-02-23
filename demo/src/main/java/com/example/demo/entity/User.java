package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    // Comment 객체의 user에 맵핑한다는 의미
    // new를 하는 이유는 유저가 있고 그다음 매핑관계에 있는 comment가 생성되기 때문
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> commentList = new ArrayList<>();

    // Article 객체의 user에 맵핑한다는 의미
    // new를 하는 이유는 유저가 있고 그다음 매핑관계에 있는 article이 생성되기 때문.
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Article> articleList = new ArrayList<>();

    // 유저 생성
    @Builder
    public User(String name) {
        this.name = name;
        this.commentList = new ArrayList<>();
        this.articleList = new ArrayList<>();
    }

    // 유저 글 생성 메서드
    public void addArticle(Article article) {
        // 해당 유저 엔티티에 게시글 넣어주고
        this.articleList.add(article);
        // 해당 게시글의 user객체를 이 user 엔티티로 선언
        article.setUser(this);
    }

    // 유저 댓글 생성 메서드
    public void addComment(Comment comment) {
        // 해당 유저 엔티티에 댓글 추가
        this.commentList.add(comment);
        // 해당 댓글 엔티티의 유저를 이 유저 엔티티로 변경
        comment.setUser(this);
    }
}
