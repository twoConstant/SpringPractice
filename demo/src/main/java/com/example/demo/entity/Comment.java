package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column(name = "content", nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)  // 연결관계 정의
    @JoinColumn(name = "user_id")       // 연결
    // comment는 기존에 존재하는 user에 연결해야하기 때문에 new User()를 하지 않는다.
    // 이 기존의 user 인스턴스에 연결해주는 역할을 하는것이 JoinColumn 것이다.
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;

    @Builder
    public Comment(String content, User user, Article article){
        this.content = content;
        this.user = user;
        this.article = article;
    }
}
