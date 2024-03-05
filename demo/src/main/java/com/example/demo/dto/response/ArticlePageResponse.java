package com.example.demo.dto.response;

import com.example.demo.entity.Article;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ArticlePageResponse {

    private List<ArticleResponse> content = new ArrayList<>();
    private Long totalElements;
    private int pageNum;

    @Builder
    public ArticlePageResponse(List<ArticleResponse> content, Long totalElements, int pageNum) {
        this.content = content;
        this.totalElements = totalElements;
        this.pageNum = pageNum;
    }
}
