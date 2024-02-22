package com.example.demo.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FindAllArticlesResponse {
    private String title;
    private String content;

    @Builder
    public FindAllArticlesResponse(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
