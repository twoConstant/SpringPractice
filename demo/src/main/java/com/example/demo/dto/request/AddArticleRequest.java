package com.example.demo.dto.request;

import com.example.demo.entity.Article;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddArticleRequest {
    private String title;
    private String content;

    public Article toArticle() {
        return Article.builder()
                .title(this.title)
                .content(this.content)
                .build();
    }
}
