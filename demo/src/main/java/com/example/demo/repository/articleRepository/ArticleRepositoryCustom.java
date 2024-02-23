package com.example.demo.repository.articleRepository;

import com.example.demo.entity.Article;

import java.util.List;

public interface ArticleRepositoryCustom {

    // 특정 유저가 쓴 게시글 조회
    List<Article> findAllArticleUserByUserId(Long userId);
}
