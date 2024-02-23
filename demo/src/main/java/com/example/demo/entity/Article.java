package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jdk.jfr.StackTrace;
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
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> commentList = new ArrayList<>();


    @Builder
    public Article(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
    }

    // 게시글 수정
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // 댓글 작성 메서드
    public void addComment(Comment comment) {
        // 해당 게시글에 Comment 추가
        this.commentList.add(comment);
        // 해당 댓글에 대응하는 article 설정
        comment.setArticle(this);
    }
}
