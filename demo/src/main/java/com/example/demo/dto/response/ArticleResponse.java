package com.example.demo.dto.response;

import com.example.demo.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
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
