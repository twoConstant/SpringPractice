package com.example.demo.dto.request;

import com.example.demo.entity.Article;
import com.example.demo.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ArticleRequest {
    private String title;
    private String content;

    public Article toArticle(User user) {
        return Article.builder()
                .title(this.title)
                .content(this.content)
                .user(user)
                .build();
    }
}
