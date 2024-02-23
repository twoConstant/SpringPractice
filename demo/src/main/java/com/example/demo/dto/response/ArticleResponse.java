package com.example.demo.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ArticleResponse {
    private String title;
    private String content;
    private String name;

    @Builder
    public ArticleResponse(String title, String content, String name) {
        this.title = title;
        this.content = content;
        this.name = name;
    }

}
